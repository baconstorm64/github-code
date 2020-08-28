const knex = require('knex')({
    client: 'pg',
    connection: {
        host: 'faraday.cse.taylor.edu',
        user: 'readonly',
        password: 'nerds4christ',
        database: 'dvdrental'
    }
});

const Hapi = require('@hapi/hapi');

const init = async() => {
    const server = Hapi.server({
        host: 'localhost',
        port: 3000
    });

    await server.register({plugin: require('blipp'), options: {showAuth: true}});

    await server.register({
        plugin: require('hapi-pino'),
        options: {
            prettyPrint: true
        }
    });

    server.route([
        {
            method: 'GET',
            path: '/',
            handler: (request, h) => {
                return 'Hello, Hapi';
            },

        },
        {
            method: 'GET',
            path: '/countries/',
            handler: (request, h) => {
                return knex
                    .select('country_id', 'country')
                    .from('country');


            },
        },
        {
            method: 'GET',
            path: '/films',
            handler: (request, h) => {
                searchKey = request.query.title;
                if(request.query.title){
                    return knex
                        .select('film_id', 'title', 'rental_rate')
                        .from('film')
                        .where('title', 'like', searchKey);
                }
                else{
                    return knex
                        .select('film_id', 'title', 'rental_rate')
                        .from('film');
                }

            },
        },

        {
            method: 'GET',
            path: '/films/{id}',
            handler: (request, h) => {
                const filmId = request.params.id;
                return knex
                    .select('film_id', 'title', 'rental_rate')
                    .from('film')
                    .where('film_id', filmId);
            },
        },
    ]);

    await server.start();
}

process.on('unhandledRejection', err => {
    console.error(err);
    process.exit(1);
});

init(); 