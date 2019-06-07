let http = require('http');
let fs = require('fs');

let handleRequest = (request, response) => {
    switch (request.url) {
        case "/main.js" :
            response.writeHead(200, {"Content-Type": "text/javascript"});
            fs.readFile('./static/js/main.js', null, function (error, data) {
                if (error) {
                    response.writeHead(404);
                    respone.write('Whoops! File not found!');
                } else {
                    response.write(data);
                }
                response.end();
            });
            break;

        case "/main.css" :
            response.writeHead(200, {"Content-Type": "text/css"});
            fs.readFile('./static/css/main.css', null, function (error, data) {
                if (error) {
                    response.writeHead(404);
                    respone.write('Whoops! File not found!');
                } else {
                    response.write(data);
                }
                response.end();
            });
            break;

        case "/config.json" :
        response.writeHead(200, {"Content-Type": "application/json"});
        fs.readFile('./config.json', null, function (error, data) {
            if (error) {
                response.writeHead(404);
                respone.write('Whoops! File not found!');
            } else {
                response.write(data);
            }
            response.end();
        });
        break;

        default :
            response.writeHead(200, {'Content-Type': 'text/html'});
            fs.readFile('./static/templates/index.html', null, function (error, data) {
                if (error) {
                    response.writeHead(404);
                    respone.write('Whoops! File not found!');
                } else {
                    response.write(data);
                }
                response.end();
            });
    }
};

http.createServer(handleRequest).listen(8081);