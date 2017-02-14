# Movie Fun Service Smoke Tests

These tests will test that the movie fun app has been successfully deployed.
They do not rely on any specific data, and therefore should work against any
deployed instance of the application.

## Usage

`bin/test <movie_fun__service_url>` where `<movie_fun_service_url>` is the base url of the
movie fun application you'd like to test. For example:

```bash
bin/test http://localhost:8181
```
