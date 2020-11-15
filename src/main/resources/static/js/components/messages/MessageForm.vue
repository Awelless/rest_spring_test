<template>
    <div>
        <input type="text" placeholder="your message..." v-model="text" />
        <input type="button" value="save" @click="save" />
    </div>
</template>

<script>
    import { sendMessage } from 'util/ws'

    export default {
        props: ['messages', 'messageAttr'],
        data() {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.text = newVal.text
                this.id   = newVal.id
            }
        },
        methods: {
            save() {
                sendMessage({id: this.id, text:this.text})
                this.text = ''
                this.id = ''
                /*
                const message = { text: this.text }

                if (this.id) {
                    this.$resource('/message{/id}').update({ id: this.id }, message).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.messages, this.id)
                            this.messages.splice(index, 1, data)
                            this.text = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/message{/id}').save({}, message).then(result =>
                        result.json().then(data => {
                            this.messages.push(data)
                            this.text = ''
                        })
                    )
                }
                */
            }
        }
    }
</script>

<style>

</style>