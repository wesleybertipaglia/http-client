# Http Client

A simple HTTP client made with java.net.http packet. The purpose of this project is just to learn how to use the java.net.http package and make HTTP requests in Java.

I have also added an example using the @Google Book API to demonstrate how to use the client.

## Table of Contents
- [Features](#features)
- [Get Started](#get-started)
- [Usage Example](#usage-example)
- [Contributing](#contributing)
- [License](#license)

## Features
- Simple and easy to use
- Supports GET, POST, PUT, DELETE, PATCH requests
- Supports JSON and XML responses
- Supports custom headers
- Supports custom query parameters
- Supports custom request body
- Supports custom timeout
- And more...

## Get Started

### Prerequisites
- Java 17+ (you can use a lower version)
- Maven

### Running the project

1. Clone the repository
```bash
git clone https://github.com/wesleybertipaglia/http-client.git
```

2. Navigate to the project directory
```bash
cd http-client
```

3. Build the project
```bash
mvn clean install
```

4. Run the project
```bash
mvn exec:java -Dexec.mainClass="com.wesleybertipaglia.Main"
```

### Usage Example

> Example using the @Google Book API

```bash
📚 BOOK SEARCH MENU
1. Search for a book
2. Exit
Choose an option: 1

Enter book title: The Hobbit

📖 SEARCH RESULTS:

🔹 Book #1
   📌 Title: The Hobbit
   📖 Subtitle: N/A
   ✍️ Authors: J.R.R. Tolkien
   🏢 Publisher: HarperCollins
   📅 Published Date: 2012-11-08
   🔗 More Info: https://www.googleapis.com/books/v1/volumes/CixXEAAAQBAJ

🔹 Book #2
   📌 Title: The Hobbit
   📖 Subtitle: N/A
   ✍️ Authors: J.R.R. Tolkien
   🏢 Publisher: HarperCollins
   📅 Published Date: 2012-02-15
   🔗 More Info: https://www.googleapis.com/books/v1/volumes/pD6arNyKyi8C
```

## Contributing

Contributions are welcome! If you have any suggestions or improvements, please open an issue or a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
