<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <v-container v-if="!profile">Необходимо авторизоваться через
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <message-list />
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import MessageList from 'components/messages/MessageList.vue'
    import { addHandler } from 'util/ws'
    import { mapState, mapMutations } from 'vuex'

    export default {
        components: {
            MessageList
        },
        computed: mapState(['profile']),
        methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error('Looks like event type is unknown "${data.eventType}"')
                    }
                } else {
                    console.error('Looks like object type is unknown "${data.objectType}"')
                }
            })
        }
    }
</script>

<style>

</style>