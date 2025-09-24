# 🍰 Saturday Cream

> A customized software product designed for dessert shops, providing both a **management system backend** for internal staff and a **mobile web ordering app** for customers.

---

## 📖 Overview

**Saturday Cream** is a full-stack dessert shop management and ordering platform.  
It is divided into two main modules:

1. **Management System Backend**  
   - Designed for dessert shop employees and administrators.  
   - Provides comprehensive tools for managing desserts, categories, set meals, orders, employees, and more.  
   - Includes analytics and reporting features for restaurant performance.

2. **Mobile Web Ordering App**  
   - Designed for customers.  
   - Allows browsing desserts online, adding items to cart, placing orders, making payments, and tracking order status.  
   - Supports convenient customer features such as reordering and delivery address management.

---

## ✅ Features

### Backend (Admin / Staff)
- 👩‍💼 **Employee Management** – Add, edit, and manage employee accounts and roles.  
- 📂 **Category Management** – Organize desserts into categories.  
- 🍩 **Dessert Management** – Maintain dessert details such as name, price, images, and availability.  
- 🍱 **Set Meal Management** – Create and manage dessert combo packages.  
- 🧾 **Order Management** – View and update customer orders.  
- 📊 **Data Statistics** – Monitor sales performance and restaurant analytics.

### Customer Web App
- 🔑 **User Login** – Customer authentication for secure access.  
- 🛒 **Shopping Cart** – Add, edit, and remove items before checkout.  
- 💳 **Order & Payment** – Place online orders and complete secure payments.  
- ⏱ **Order Tracking** – View order status and send reminders.  
- 📜 **Order History** *(in progress)* – View past orders.  
- 📍 **Address Management** *(in progress)* – Save and manage delivery addresses.

---

## 🚧 Current Status
- Completed: **Employee Management, Category Management, Dessert Management, Set Meal Management, Order Management, Data Statistics, User Login, Shopping Cart, Ordering, Payment.**  
- In Development: **Order History, Address Management.**

---

## 🖼️ Preview
![Saturday Cream Demo](docs/images/demo.png)

---

## 🛠️ Tech Stack

### Backend
- **Spring Boot** – Application framework  
- **Spring MVC** – RESTful web services  
- **MyBatis + PageHelper** – ORM & pagination  
- **Spring Data Redis** – Caching and session management  
- **Redis** – In-memory data store  
- **MySQL** – Relational database  
- **JWT (JSON Web Token)** – Authentication & authorization  
- **Swagger** – API documentation  
- **WebSocket** – Real-time messaging (order status updates, notifications)  
- **HttpClient** – External service integration


### Deployment
- **Docker** – Containerized deployment  
- **AWS** – Cloud hosting and scalability

---

## 📌 Roadmap
- [ ] Finish Order History module  
- [ ] Add Address Management  
- [ ] Improve Analytics Dashboard  
- [ ] Enhance Payment Integration  
- [ ] Add Notification System with WebSocket

---

## 📄 License
MIT License.  
Feel free to fork and adapt this project for your own dessert shop.

---
