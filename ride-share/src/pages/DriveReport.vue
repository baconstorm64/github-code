<template>
    <v-container>
        <div>
            <h4 class="display-1">Drives</h4>

            <v-data-table
            >
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
                drivereport: [],

                snackbar: {
                    show: false,
                    text: ""
                }
            };
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
