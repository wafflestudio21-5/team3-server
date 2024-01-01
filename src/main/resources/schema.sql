-- Drop tables if they already exist
DROP TABLE IF EXISTS comment_likes;
DROP TABLE IF EXISTS post_likes;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

-- Users Table
CREATE TABLE users (
    userId BIGINT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Posts Table
CREATE TABLE posts (
    postId BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    category VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    likes INT DEFAULT 0,
    FOREIGN KEY (userId) REFERENCES users(userId)
);

-- Comments Table
CREATE TABLE comments (
    commentId BIGINT AUTO_INCREMENT PRIMARY KEY,
    postId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    content TEXT NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    parentCommentId BIGINT,
    likes INT DEFAULT 0,
    FOREIGN KEY (postId) REFERENCES posts(postId),
    FOREIGN KEY (userId) REFERENCES users(userId),
    FOREIGN KEY (parentCommentId) REFERENCES comments(commentId)
);

-- Post Likes Table
CREATE TABLE post_likes (
    postLikeId BIGINT AUTO_INCREMENT PRIMARY KEY,
    postId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    UNIQUE (postId, userId),
    FOREIGN KEY (postId) REFERENCES posts(postId),
    FOREIGN KEY (userId) REFERENCES users(userId)
);

-- Comment Likes Table
CREATE TABLE comment_likes (
    commentLikeId BIGINT AUTO_INCREMENT PRIMARY KEY,
    commentId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    UNIQUE (commentId, userId),
    FOREIGN KEY (commentId) REFERENCES comments(commentId),
    FOREIGN KEY (userId) REFERENCES users(userId)
);
