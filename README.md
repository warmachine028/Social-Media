# Social Media

## Table of Contents

- [Introduction](#introduction)
- [Requirement](#requirement)

## Introduction

- Implementation of a Social Media Application
- Using Class Heirarchy
- Handle Object Relationships

## Requirement

- Create Classes for
    - User
        ```
        public class social.media.User {
            public social.media.User(java.lang.String, java.lang.String, java.lang.String, java.lang.String);
            void forgetPassword();
            void resetPassword(java.lang.String);
            public void displayInfo();
            void displayAvatar();
            public long getId();
            public void like(social.media.Post);
            void commentPost(social.media.Post);
            public void deletePost(social.media.Post);
            public social.media.Post createPost();
        }
        ```
    - Post
        ```
        public class social.media.Post {
            public social.media.Post(long, java.lang.String, java.lang.String);
            public social.media.Post(long, java.lang.String, java.lang.String, java.lang.String);
            public social.media.Post(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String[]);
            public social.media.Post(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], social.media.utlis.Visibility);
            public int getLikes();
            public void showLikes();
            int editPost(java.lang.String, java.lang.String);
            int editPost(java.lang.String, java.lang.String, java.lang.String);
            social.media.utlis.Visibility getVisibility();
            long getCreator();
            long getId();
            java.sql.Timestamp getCreatedAt();
            void setPrivate();
            void setPublic();
            void likePost(long);
            void commentPost(long);
        }
        ```
    - Comments
        ```
        public class social.media.Comment {
            social.media.Comment(long, long, java.lang.String);
            long getId();
            long getCreatorId();
            long getPostId();
            java.lang.String getMessage();
        }
        ```
