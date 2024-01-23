-- Drop tables if they already exist
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS message_sessions;
DROP TABLE IF EXISTS scraps;
DROP TABLE IF EXISTS comment_likes;
DROP TABLE IF EXISTS post_likes;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS user_details;
DROP TABLE IF EXISTS users;

-- Users Table
CREATE TABLE users (
   user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
   user_name VARCHAR(255) UNIQUE NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) UNIQUE NOT NULL
);

-- User Details Table
CREATE TABLE user_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    real_name VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    student_id INT NOT NULL,
    user_id BIGINT UNIQUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Posts Table
CREATE TABLE posts (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    category VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    likes INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Comments Table
CREATE TABLE comments (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    parent_comment_id BIGINT,
    likes INT DEFAULT 0,
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (parent_comment_id) REFERENCES comments(comment_id)
);

-- Post Likes Table
CREATE TABLE post_likes (
    post_like_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    UNIQUE (post_id, user_id),
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Comment Likes Table
CREATE TABLE comment_likes (
    comment_like_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    UNIQUE (comment_id, user_id),
    FOREIGN KEY (comment_id) REFERENCES comments(comment_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Scraps Table
CREATE TABLE scraps (
    scrap_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    UNIQUE (post_id, user_id),
    FOREIGN KEY (post_id) REFERENCES posts(post_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Message Sessions Table
CREATE TABLE message_sessions (
    session_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user1_id BIGINT NOT NULL,
    user2_id BIGINT NOT NULL,
    FOREIGN KEY (user1_id) REFERENCES users(user_id),
    FOREIGN KEY (user2_id) REFERENCES users(user_id)
);

-- Messages Table
CREATE TABLE messages (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    session_id BIGINT NOT NULL,
    sender_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES message_sessions(session_id),
    FOREIGN KEY (sender_id) REFERENCES users(user_id)
);
