const knexModule = require('knex');
const knex = knexModule({
    client: 'pg',
    connection: {
        host: 'faraday.cse.taylor.edu',
        user: 'readonly',
        password: 'nerds4christ',
        database: 'dvdrental'
    }

});

function firstQuery(){
    query = knex
        .select('category_id', 'name')
        .from('category')
        .then(result => console.log(result))
        .then(() => knex.destroy())
        .catch(err => console.log(err));
}

function secondQuery(){
    query = knex
        .select('title', 'rental_duration')
        .from('film')
        .where('title', 'like', 'S%')
        .where('rental_duration', 'between', [4,6])
        .orderBy('rental_duration', 'desc')
        .orderBy('title')
        .then(result => console.log(result))
        .then(() => knex.destroy())
        .catch(err => console.log(err));
}

function thirdQuery(){
    query = knex
        .select('country')
        .count('city_id AS city_count')
        .from('city')
        .innerJoin('country', 'city.country_id', 'country.country_id')
        .groupBy('country')
        .orderBy('city_count', 'desc')
        .then(result => console.log(result))
        .then(() => knex.destroy())
        .catch(err => console.log(err));
}

firstQuery();
secondQuery();
thirdQuery();