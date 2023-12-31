# SDA-Orders-System

## Description
SDA-Orders-System is a software system designed to manage orders. It provides functionalities such as creating, updating, deleting, and viewing orders.

## Installation
1. Clone the repository: `git clone https://github.com/username/SDA-Orders-System.git`
2. Navigate into the project directory: `cd SDA-Orders-System`
3. Build the project with Maven: `mvn clean install`
4. Run the project: `mvn spring-boot:run`

## Usage
After successfully building and running the project, you can access the application at `http://localhost:8080` in your web browser.

## Features
- Order Creation: Allows users to create new orders.
- Order Viewing: Allows users to view their existing orders.
- Order Updating: Allows users to update their existing orders.
- Order Deletion: Allows users to delete their orders.
- Notification System: Notifies users about updates on their orders.
  - Email Notifications: Sends email notifications to users when there are updates on their orders.
  - SMS Notifications: Sends SMS notifications to users when there are updates on their orders.
  - Notification Preferences: Allows users to choose which notification channels they want to receive notifications from.
  - Notifications are made using Message Queue architecture to ensure that the system is scalable and reliable.
- User Management: Allows users to manage their accounts.
    - User Registration: Allows users to register for an account.
    - User Login: Allows users to login to their accounts.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)