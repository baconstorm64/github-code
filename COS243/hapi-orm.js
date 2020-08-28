const knex = require('knex')({
    client: 'pg',
    connection: {
        host: 'faraday.cse.taylor.edu',
        user: 'readonly',
        password: 'nerds4christ',
        database: 'dvdrental'
    }
});

const { Model } = require("objection");
Model.knex(knex);

const { Actor } = require("./Actor");
Actor.knex(knex);

const { Film } = require("./Film");
Film.knex(knex);

const { ref } = require("objection");


const Hapi = require("@hapi/hapi");

const init = async () => {
    const server = Hapi.server({
        host: "localhost",
        port: 3000
    });

    await server.register({
        plugin: require("hapi-pino"),
        options: {
            prettyPrint: true,
        },
    });

    server.route([
    {
        method: "GET",
        path: "/",
        handler: function (request, h){
            return "Hello, Hapi";
        },
    },
    {
        method: "GET",
        path: "/films/",
        handler: function (request, h){
          return Film.query();
        },
    },
    {
        method: "GET",
        path: "/actors/",
        handler: function (request, h){
            return Actor.query();
        },
    },
    {
        method: "GET",
        path: "/films/{id}",
        handler: function (request, h){
            const filmId = request.params.id;
            return Film.query()
                .where('film_id', filmId)
                .withGraphFetched('actors').first();
        },
    },
    {
        method: "GET",
        path: "/actors/{id}",
        handler: function (request, h){
            const actorId = request.params.id;
            return Actor.query()
                .where('actor_id', actorId)
                .withGraphFetched('films').first();
        }
    }
    ]);

    console.log("Server listening on", server.info.uri);
    await server.start();
}

init();
