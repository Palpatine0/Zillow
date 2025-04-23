<template>
<v-app>
    <v-layout row style="background-color: #F5F5F5">
        <v-flex md3>
            <v-container>
                <v-flex class="ml-8">
                    <v-card class="ma-5 text-center mt-12" shaped>
                        <v-avatar class="mt-n7" size="110">
                            <img alt="Palpatine" src="/avatar-1.png">
                        </v-avatar>
                        <v-card-title class="layout justify-center">Palpatine</v-card-title>
                        <!--<v-card-subtitle class="layout justify-center">24 year, California</v-card-subtitle>-->
                        <!--<v-list>
                            <v-list-item>
                                <v-list-item-title class="cyan--text text--darken-1">Blood</v-list-item-title>
                                <v-list-item-title class="cyan--text text--darken-1">height</v-list-item-title>
                                <v-list-item-title class="cyan--text text--darken-1">Weight</v-list-item-title>
                            </v-list-item>
                            <v-list-item class="mt-n5">
                                <v-list-item-subtitle>/</v-list-item-subtitle>
                                <v-list-item-subtitle>/</v-list-item-subtitle>
                                <v-list-item-subtitle>/</v-list-item-subtitle>
                            </v-list-item>
                        </v-list>-->
                    </v-card>
                </v-flex>
                <v-flex class="ml-8 mt-12">
                    <v-row justify="center">
                        <v-date-picker
                            v-model="datePicker"
                            :event-color="date => date[9] % 2 ? 'red' : 'yellow'"
                            :events="datePickerFunctionEvents"
                            color="info"
                        ></v-date-picker>
                    </v-row>
                </v-flex>
            </v-container>
        </v-flex>
        <v-flex md9>
            <v-container class="white" style="margin-top: 12px;padding: 10px 30px 40px 30px;border-radius: 40px">
                <v-row>
                    <v-col cols="12" md="4">
                        <h2 class="mt-5" :style="{ color: $vuetify.theme.themes.light.secondary, fontWeight: 'bold' }">Real Estate Market Pulse</h2>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="4">
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-card height="120px" width="10px" color="green"></v-card>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-list two-line subheader class="ml-n8">
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-list-item-subtitle>Q3 2024</v-list-item-subtitle>
                                            <v-list-item-title>Housing Vacancy Rates</v-list-item-title>
                                            <p style="color: #848b8d;font-size: 13px">{{ housingVacanciesSummary }}</p>
                                        </v-list-item-content>
                                    </v-list-item>
                                </v-list>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col cols="12" md="4">
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-card height="120px" width="10px" color="red"></v-card>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-list two-line subheader class="ml-n8">
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-list-item-subtitle>Q3 2024</v-list-item-subtitle>
                                            <v-list-item-title>Homeownership Rates</v-list-item-title>
                                            <p style="color: #848b8d;font-size: 13px">{{ homeownershipSummary }}</p>
                                        </v-list-item-content>
                                    </v-list-item>
                                </v-list>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col cols="12" md="4">
                        <v-row>
                            <v-col cols="12" md="2">
                                <v-card height="120px" width="10px" color="grey"></v-card>
                            </v-col>
                            <v-col cols="12" md="10">
                                <v-list two-line subheader class="ml-n8">
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-list-item-subtitle>Q3 2024</v-list-item-subtitle>
                                            <v-list-item-title>Estimated Rented Units</v-list-item-title>
                                            <p style="color: #848b8d;font-size: 13px">{{ estimatedRentedUnitsSummary }}</p>
                                        </v-list-item-content>
                                    </v-list-item>
                                </v-list>
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
            <v-container class="white" style="margin-top: 12px;padding: 10px 30px 60px 30px;border-radius: 40px">
                <v-row>
                    <v-col cols="12" md="12">
                        <h2 class="mt-5" :style="{ color: $vuetify.theme.themes.light.secondary, fontWeight: 'bold' }">New Residential Construction Recently</h2>
                    </v-col>
                </v-row>
                <v-row>
                    <v-sparkline
                        :value="nrcValue"
                        :gradient="['#f72047', '#ffd200', '#1feaea']"
                        smooth="10"
                        padding="8"
                        line-width="2"
                        auto-draw
                    ></v-sparkline>
                    <span v-html="renderMarkdown(nrcSummary)"></span>
                </v-row>
            </v-container>
        </v-flex>
    </v-layout>
</v-app>
</template>

<script>
import MarkdownIt from 'markdown-it';

const md = new MarkdownIt({
    breaks: true,
    html: true
});

export default {
    name: "Dashboard",
    data() {
        return {
            datePicker: new Date().toISOString().substr(0, 10),

            nrcValue: [],
            nrcSummary: '',
            housingVacanciesSummary: '',
            homeownershipSummary: '',
            estimatedRentedUnitsSummary: '',
        }
    },
    mounted() {
        this.getNewConstructionStatValues()
        this.getNewConstructionStatSummary()
        this.getHousingVacanciesStatSummary()
        this.getHomeownershipStatSummary()
        this.getEstimatedRentedUnitsStatSummary()
    },
    methods: {
        getNewConstructionStatValues() {
            this.$api.llm.getNewConstructionStatValues({})
            .then((res) => {
                this.nrcValue = res.data;
            })
        },
        getNewConstructionStatSummary() {
            this.$api.llm.getNewConstructionStatSummary({})
            .then((res) => {
                this.nrcSummary = res.data;
            })
        },
        getHousingVacanciesStatSummary() {
            this.$api.llm.getHousingVacanciesStatSummary({})
            .then((res) => {
                this.housingVacanciesSummary = res.data;
            })
        },
        getHomeownershipStatSummary() {
            this.$api.llm.getHomeownershipStatSummary({})
            .then((res) => {
                this.homeownershipSummary = res.data;
            })
        },
        getEstimatedRentedUnitsStatSummary() {
            this.$api.llm.getEstimatedRentedUnitsStatSummary({})
            .then((res) => {
                this.estimatedRentedUnitsSummary = res.data;
            })
        },

        renderMarkdown(text) {
            return md.render(text);
        },
        datePickerFunctionEvents(date) {
            const [, , day] = date.split('-')
            if([12, 17, 28].includes(parseInt(day, 10))) return true
            if([1, 19, 22].includes(parseInt(day, 10))) return ['red', '#00f']
            return false
        },
    },

}
</script>

<style>
li {
    display: list-item !important;
    margin-bottom: 18px !important;
}
</style>
