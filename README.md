Original App Design Project - README Template
===

# RepoDepo

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
   - This app will encompass a social media layout where you can follow and connect with people from all over the place
   - Has a built-in Github integration that portrays an upvote system of Github repositories so people can see what projects they would want to connect with other people to work on
   - A location system that would show locations of individual programmers that are near a user for connection purposes (since no one wants to do anything online anymore cause covid)

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:**
- **Mobile:**
- **Story:**
- **Market:**
- **Habit:**
- **Scope:**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

**Home Screen**
- [ ] User can view repositories  
- [x] User is displayed the username, name, and body for each repository
- [x] User can refresh tweets timeline by pulling down to refresh
- [ ] User can like repositoriesUser can login

**Login Screen**
- [x] User can register account
- [x] User can enter username/password 
- [x] User can login to account 

**Profile Screen**
- [ ] User can see their own repositories 
- [ ] User can refresh for new repositories 
- [ ] User can add new repositories

**Dm Screen**
- [ ]  User can send messages 
- [ ] User can receive messages 

**Github Api**
- [x] Connect to GitHub repository
- [x] User is displayed the username, name, and body for each repository


**Optional Nice-to-have Stories**

- [ ] User can view locations of other users in surrounding area.

### 2. Screen Archetypes

* <img src="login.jpg" width="500" height="500">

   * Login
      * User can login
      * User can register account
      * User can enter username/password 
      * User can login to account 


* <img src="home.jpg" width="500" height="500">

   * Home
      * User can view repositories  
      * User is displayed the username, name, and body for each repository
      * User can refresh tweets timeline by pulling down to refresh
      * User can like repositoriesUser can login
      * User can view other users github


   * Profile
      * User can update profile
      * User can see their own repositories 
      * User can refresh for new repositories 
      * User can add new repositories

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* Profile
* DM

**Flow Navigation** (Screen to Screen)

* Home
   * Profile
   * Dm
* Profile
   * Home
   * Dm
 * Dm
   * Home
   * Profile



## Wireframes

<img src="map_screen.jpg" width="500" height="500">

### [BONUS] Digital Wireframes & Mockups
### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models

## Networking
* Home Screen
   * (Read/GET) Get/View Respositories and their details
   * (Read/GET) Get likes on a repository 
   * (Read/GET) Get Gif on selective repositories
* Dm Screen
   * (Create/POST) Create a message 
   * (Read/GET) Getting recieving message 
* Profile Screen
   * (Delete/DELETE) Deleting a repository 
   * (Read/GET) Get/View Repositories and their details
   * (Read/GET) Creating a New Repository with details
   * (Read/GET) Get Gif on selective repositories
## GitHub API
Base URL - https://docs.github.com/en/rest
  | CRUD      | HTTP Verb     | Description |
   | ------------- | -------- | ------------|
   | Create      | `POST`   | Creating a New Repository with details |
   | Read        |`GET` | Get/View Repositories and their details |
   | Delete         | `DELETE`     | Deleting a repository  |
   | Read       | `GET`   | Get likes on a repository  |
   | Create | `POST`   | Create a message  |
   | Read    | `GET`   | Getting recieving message   |
   | Read     | `GET` | Get Gif on selective repositories |
