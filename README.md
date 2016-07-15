[![Dependency Status](https://www.versioneye.com/user/projects/575e949800863f000db9568a/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/575e949800863f000db9568a)

# versioneye-core-j

This project contains the persistence and services for VersionEye implemented in Java.

## Start the backend services for VersionEye

This project contains a [docker-compose.yml](docker-compose.yml) file which describes the backend services
of VersionEye. You can start the backend services like this:

```
docker-compose up -d
```

That will start:

 - MongoDB
 - RabbitMQ
 - ElasticSearch
 - Memcached

For persistence you should comment in and adjust the mount volumes in [docker-compose.yml](docker-compose.yml)
for MongoDB and ElasticSearch. If you are not interested in persisting the data on your host you can
let it untouched.

Shutting down the backend services works like this:

```
docker-compose down
```

## MongoDB Config

As primary database we are using MongoDB. To make this project work you need to configure
the MongoDB connection in `src/test/resources/mongo.properties`.
If you run MongoDB as a single instance, only fill out the first 3 lines.

## Tests

Run the tests like this:

```
mvn clean test
```

## Support

For commercial support send a message to `support@versioneye.com`.

## License

this project is licensed under the MIT license!

Copyright (c) 2016 VersionEye GmbH

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
