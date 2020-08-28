<template>
    <v-container>
        <div>
            <h4 class="display-1">Rides</h4>

            <v-data-table
                    class="elevation-1"
                    v-bind:headers="headers"
                    v-bind:items="ridereport"
            >
                <template v-slot:item="{ item }">
                    <tr>
                        <td>{{ item.time }}</td>
                        <td>{{ item.date }}</td>
                        <td>{{ item.distance }}</td>
                        <td>{{ item.fuelprice }}</td>
                        <td>{{ item.fee }}</td>
                    </tr>
                </template>
            </v-data-table>

            <v-snackbar v-model="snackbar.show">
                {{ snackbar.text }}
                <v-btn color="blue" text @click="snackbar.show = false">
                    Close
                </v-btn>
            </v-snackbar>
        </div>
    </v-container>
</template>

<script>
    export default {
        name: "Rides",

        data: function() {
            return {
                headers: [
                    { text: "Time", value: "time" },
                    { text: "Date", value: "date" },
                    { text: "Distance", value: "distance" },
                    { text: "Fuel Price", value: "fuelprice" },
                    { text: "Fee", value: "fee" },
                ],
                ridereport: [],

                snackbar: {
                    show: false,
                    text: ""
                }
            };
        },

        mounted: function() {
            this.$axios.get("/ridereport").then(response => {
                this.ridereport = response.data.map(ridereport => ({
                    date: ridereport.date,
                    time: ridereport.time,
                    distance: ridereport.distance,
                    fuelprice: ridereport.fuelprice,
                    fee: ridereport.fee
                }));
            });
        },

        methods: {
            // Display a snackbar message.
            showSnackbar(text) {
                this.snackbar.text = text;
                this.snackbar.show = true;
            },
        }
    };
</script>
