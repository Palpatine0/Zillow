# Zillow

## Introduction

Welcome to the Zillow Imitation Project. 
This repository houses a Spring Cloud-based house rental application that closely imitates the functionalities and design of Zillow. 
This project serves as a practical exploration of microservices architecture using modern technologies like MongoDB, Redis, Elasticsearch, and RabbitMQ.

## Features

- `Microservices Architecture`: Built with Spring Boot and Spring Cloud for scalable and maintainable service management.
- `Property Listings`: Users can view a variety of rental properties, each detailed with photos, prices, and descriptions.
- `Search Functionality`: Robust search capabilities powered by Elasticsearch, allowing filters for location, price range, number of bedrooms, and more.
- `Client Portal`: A user-friendly interface for tenants and potential renters to browse properties, manage accounts, and interact with property managers.
- `Admin Panel`: A dedicated platform for administrators to manage property listings, user inquiries, and system settings.
- `Data Storage`: Uses MongoDB for flexible and efficient data storage solutions.
- `Chatbot`: An AI-powered assistant for users to get instant help with rental property searches, account management, and general inquiries.

## Technologies Used

- `Spring Boot`: Simplifies the bootstrapping and development of new Spring applications.
- `Spring Cloud`: Facilitates building robust microservices using patterns like service discovery, configuration management, and routing.
- `MongoDB`: NoSQL database used for storing application data.
- `Redis`: In-memory data structure store, used as a database, cache, and message broker.
- `Elasticsearch`: Search engine based on the Lucene library, perfect for powerful search functionalities.
- `RabbitMQ`: Advanced message queuing protocol used for handling service-to-service communication.
- `Vue.js` and `Vuetify`: Progressive JavaScript framework and material design component framework used for building both the client app and admin panel.
- `LangChain`: Framework for building applications with LLMs (Large Language Models), enabling intelligent query processing and conversational AI.
- `OpenAI API`: For natural language processing and chatbot functionality.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java JDK 8
- Maven 3.6.3
- Your preferred IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

#### Project Installation

1. Clone the repository:

```bash
git clone git@github.com:Palpatine0/Zillow.git
```

2. Navigate to the project directory:

```bash
cd Zillow
```

3. Build the project with Maven:

```bash
mvn clean install
```
#### Consul Deployment

1. Create container

```bash
docker run -d -p 8500:8500 -p 8600:8600/udp --name=dev_zillow_svc_consul consul:1.14.0
```

#### Redis Deployment

1. Create container

```bash
docker run -d -p 6379:6379 --name dev_zillow_svc_redis redis:7.2-rc2 --requirepass root
```

#### MongoDB Deployment

1. Create container

```bash
docker run -d -p 27017:27017 --name dev_zillow_svc_mongodb mongo:4.4.19-rc2 --auth
```

2. Enter MongoDB

```bash
docker exec -it dev_zillow_svc_mongodb bash
```

```bash
mongo
```

3. Set authentication

```bash
use admin
db.createUser({ user: "root", pwd: "root", roles: [{ role: "root", db: "admin" }] })
db.auth("root", "root");
```

### Application Ports

#### Core Services

- **Consul server:** 8500
- **Config server:** 3355
- **Gateway server:** 4006

#### Microservices

- **File:** 9000
- **Trendy:** 9001
- **Recommendation:** 9002
- **Search:** 9003
- **Item:** 9004
- **Comment:** 9005
- **Buy Time:** 9006
- **User:** 9007
- **Buy Action:** 9008
- **Order:** 9009
- **LLM:** 9010

#### Databases

- **MongoDB:** 27017
- **Redis:** 6379

#### UI

- **Client:** 3010
- **Admin Dashboard:** 3011

## Contributing

We welcome contributions! Any contributions are greatly appreciated.

#### To contribute:

1. Fork the Project
2. Create your Feature Branch (git checkout -b feature/AmazingFeature)
3. Commit your Changes (git commit -m 'Add some AmazingFeature')
4. Push to the Branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

## License

This project is distributed under the MIT License. See the LICENSE file for more information.

## Contact

- Project Link: `https://github.com/Palpatine0/Zillow`

- Snapchat: `emperorsidious0`

<hr>

## Preview

### Client

<div align="center">
    <img style="width: 200px" src="https://assets.leetcode.com/users/images/43565cd9-5985-4872-a067-94ebd6d36f8c_1715997598.9587522.png">
    <img style="width: 200px" src="https://assets.leetcode.com/users/images/12c31fb7-6cb7-47e6-a004-68c8150d0516_1715997945.7497053.jpeg">
    <img style="width: 200px" src="https://assets.leetcode.com/users/images/6bc2cc33-9496-4d55-b9b7-73ee1cce9a3f_1715998044.8705413.jpeg">
    <img style="width: 200px" src="https://assets.leetcode.com/users/images/dc01547c-7343-4e61-9253-54cc9624d9cb_1715998512.2449012.jpeg">
</div>

### Admin

<div align="center">
    <img style="width: 400px" src="https://assets.leetcode.com/users/images/ce07d4fb-665d-45b6-b558-ba4932a5fe47_1715999207.1361368.jpeg">
    <img style="width: 400px" src="https://assets.leetcode.com/users/images/e7b1b804-8c0b-45b8-80cf-72b3f7633779_1715998831.4935336.jpeg"><br>
    <img style="width: 400px" src="https://assets.leetcode.com/users/images/e6acff3d-fc81-4408-ad71-d7dc7aeaffa9_1715999338.097314.jpeg">
    <img style="width: 400px" src="https://assets.leetcode.com/users/images/a776fa62-4f2e-4a0e-a0c0-d0f3fe785607_1715999410.477739.jpeg"><br>
</div>