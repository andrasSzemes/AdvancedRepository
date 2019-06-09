let http = require('http');
let fs = require('fs');


var finalhandler = require('finalhandler');
var serveStatic = require('serve-static');

var serve = serveStatic("./");

var server = http.createServer(function(req, res) {
    if (req.url == "/") {
        res.writeHead(200, {'Content-Type': 'text/html'});
        fs.readFile('./static/templates/index.html', null, function (error, data) {
            if (error) {
                res.writeHead(404);
                respone.write('Whoops! File not found!');
            } else {
                res.write(data);
            }
            res.end();
        });
    }

    var done = finalhandler(req, res);
    serve(req, res, done);
});

server.listen(8081);