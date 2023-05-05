const express = require('express');
const mustacheExpress = require('mustache-express');
const postsRouter = require('./posts.js').router;
const postsInit = require('./posts.js').init;

const APP_PORT = 5000;

const app = express();

// Register '.html' extension with The Mustache Express
app.set('view engine', 'html');
app.set('views', __dirname + '/views');
app.engine('html', mustacheExpress());

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

app.use(postsRouter);

process.on('SIGINT', () => {
    process.exit(0);
});

async function main() {
    await postsInit();

    app.listen(APP_PORT, () => {
        console.log(`* Running on http://localhost:${APP_PORT}/`);
        console.log("  (Press CTRL+C to quit)");
    });
}

main();