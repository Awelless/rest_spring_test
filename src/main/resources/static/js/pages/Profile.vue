<template>
    <v-container>
        <v-layout justify-space-around>
            <v-flex xs6>
                <div class="title mb-3">User profile</div>
                <v-layout row justify-space-between>
                    <v-flex class="px-1">
                        <v-img :src="profile.userpic"></v-img>
                    </v-flex>
                    <v-flex class="px-1">
                        <v-layout column>
                            <v-flex>{{profile.name}}</v-flex>
                            <v-flex>{{profile.locale}}</v-flex>
                            <v-flex>{{profile.gender}}</v-flex>
                            <v-flex>{{profile.lastVisit}}</v-flex>
                            <v-flex>
                                {{profile.subscriptions && profile.subscriptions.length}} subscriptions
                            </v-flex>
                            <router-link
                                v-if="isMyProfile"
                                :to="`/subscriptions/${profile.id}`"
                            >
                                {{profile.subscribers && profile.subscribers.length}} subscribers
                            </router-link>
                            <v-flex
                                v-else
                            >
                                {{profile.subscribers && profile.subscribers.length}} subscribers
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
                <v-btn
                        class="mt-2"
                        v-if="!isMyProfile"
                        @click="changeSubscription"
                >
                    {{amISubscribed ? 'Unsubscribe' : 'Subscribe'}}
                </v-btn>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import profileApi from 'static/js/api/profile'

    export default {
        name: 'Profile',
        data() {
            return {
                profile: {}
            }
        },
        computed: {
            isMyProfile() {
                return !this.$route.params.id || this.$route.params.id === this.$store.state.profile.id
            },
            amISubscribed() {
                const subscribers = this.profile.subscribers
                return subscribers &&
                    subscribers.find(subscription => {
                        return subscription.subscriber ===  this.$store.state.profile.id
                    })
            }
        },
        watch: {
            '$route'() {
                this.updateProfile()
            }
        },
        methods: {
            async changeSubscription() {
                const data   = await profileApi.changeSubscription(this.profile.id)
                this.profile = await data.json()
            },
            async updateProfile() {
                const id = this.$route.params.id || this.$store.state.profile.id

                const data   = await profileApi.get(id)
                this.profile = await data.json()

                this.$forceUpdate()
            }
        },
        beforeMount() {
            this.updateProfile()
        }
    }
</script>

<style scoped>
    img {
        max-width: 100%;
        height: auto;
    }
</style>