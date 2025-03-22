-- 1️⃣ Create Database
CREATE DATABASE club_portal;
USE club_portal;

-- 2️⃣ Create Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    department VARCHAR(50) GENERATED ALWAYS AS 
      (SUBSTRING_INDEX(SUBSTRING_INDEX(email, '@', 1), '.', -1)) STORED,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3️⃣ Create Clubs Table
CREATE TABLE clubs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4️⃣ Create Club Membership Table
CREATE TABLE club_members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    club_id INT,
    role ENUM('member', 'coordinator') DEFAULT 'member',
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE CASCADE
);

-- 5️⃣ Create Events Table
CREATE TABLE events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    club_id INT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    event_date DATETIME NOT NULL,
    registration_fee_members DECIMAL(10,2) DEFAULT 0.00,
    registration_fee_non_members DECIMAL(10,2) DEFAULT 0.00,
    image_url VARCHAR(255),  -- Stores event image link
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE CASCADE
);

-- 6️⃣ Create Event Registrations Table (Payment & Signups)
CREATE TABLE event_registrations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    event_id INT,
    payment_status ENUM('pending', 'completed') DEFAULT 'pending',
    amount_paid DECIMAL(10,2),
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);

-- 7️⃣ Create Event Posts Table (Users Upload Event Photos)
CREATE TABLE event_posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    event_id INT,
    image_url VARCHAR(255) NOT NULL,
    caption TEXT,
    posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);

-- 8️⃣ Create Event Post Comments Table (For Post Comments)
CREATE TABLE event_post_comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id INT,
    comment TEXT NOT NULL,
    commented_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES event_posts(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 9️⃣ Create Followers Table (Follow Users in the Same Club)
CREATE TABLE followers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    follower_id INT,
    following_id INT,
    club_id INT,
    followed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (follower_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (following_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (club_id) REFERENCES clubs(id) ON DELETE CASCADE
);

-- 🔟 Create Messages Table (For DM Feature)
CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    receiver_id INT,
    message TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
);
ALTER TABLE events ADD COLUMN visibility ENUM('public', 'members_only') DEFAULT 'public';

show tables;
select * from users;
ALTER TABLE users 
DROP COLUMN department,
ADD COLUMN department VARCHAR(50) GENERATED ALWAYS AS 
(REGEXP_REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(email, '@', 1), '.', -1), '[0-9]', '')) STORED;

-- Update club_members
ALTER TABLE club_members DROP FOREIGN KEY club_members_ibfk_1;
ALTER TABLE club_members ADD CONSTRAINT club_members_fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

-- Update event_registrations
ALTER TABLE event_registrations DROP FOREIGN KEY event_registrations_ibfk_1;
ALTER TABLE event_registrations ADD CONSTRAINT event_registrations_fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

-- Update event_posts
ALTER TABLE event_posts DROP FOREIGN KEY event_posts_ibfk_1;
ALTER TABLE event_posts ADD CONSTRAINT event_posts_fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

-- Update event_post_comments
ALTER TABLE event_post_comments DROP FOREIGN KEY event_post_comments_ibfk_2;
ALTER TABLE event_post_comments ADD CONSTRAINT event_post_comments_fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

-- Update followers
ALTER TABLE followers DROP FOREIGN KEY followers_ibfk_1;
ALTER TABLE followers ADD CONSTRAINT followers_fk_follower FOREIGN KEY (follower_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE followers DROP FOREIGN KEY followers_ibfk_2;
ALTER TABLE followers ADD CONSTRAINT followers_fk_following FOREIGN KEY (following_id) REFERENCES users(id) ON DELETE CASCADE;

-- Update messages
ALTER TABLE messages DROP FOREIGN KEY messages_ibfk_1;
ALTER TABLE messages ADD CONSTRAINT messages_fk_sender FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE messages DROP FOREIGN KEY messages_ibfk_2;
ALTER TABLE messages ADD CONSTRAINT messages_fk_receiver FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE;

DELETE FROM users WHERE id = 5;
show tables;
select * from club_members;
DESC users;
DESC club_members;
ALTER TABLE club_members MODIFY COLUMN user_id INT;


desc clubs;
select * from clubs;
