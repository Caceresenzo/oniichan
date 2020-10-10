<p align="center">
  <img width="460" height="300" src="https://oniichan.app/static/sparkle.gif">
</p>

# OniiChan

A fun little project using Spring Boot for my home-made services.

- [OniiChan](#oniichan)
	- [Why](#why)
	- [Back-end](#back-end)
		- [Modules](#modules)
	- [Front-end](#front-end)
		- [Modules](#modules-1)

## Why

For the fun! And also because I bought the [oniichan.app](https://oniichan.app) domain name and now I have to make a use out of it... 

## Back-end

### Modules

| Name   | Base Endpoint | Description                                |
|--------|---------------|--------------------------------------------|
| Auth   | /auth         | Authentication                             |
| Common |               | Commonly shared classes                    |
| Core   |               | Core project, mostly include other modules |
| Drive  | /upload       | A simple file sharing system               |
| User   | /user         | User management                            |

## Front-end

### Modules

| Name  | Subdomain | Description    |
|-------|-----------|----------------|
| Index | .         | Index          |
| Auth  | auth.     | Authentication |