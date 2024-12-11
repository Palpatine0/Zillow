<template>
<div :class="!isMobile ? 'chat-box' : 'chat-box-mobile'" style="z-index: 10000">
    <!-- Chat Bot Button -->
    <div v-if="!isChatOpen">
        <v-btn
            elevation="2"
            text
            fab
            style="background-color: #FFF"
            @click="toggleChat"
        >
            <span style="font-size: 20px">🤖</span>
        </v-btn>
    </div>

    <!-- Chat Window -->
    <div v-else class="chat-window">
        <div class="chat-header" style="justify-content: flex-end">
            <v-btn
                icon
                small
                style="background-color: rgba(55, 53, 47, 0.04)"
                @click="toggleChat"
            >
                <v-icon size="16">mdi-close</v-icon>
            </v-btn>
        </div>
        <div class="chat-body">
            <div
                v-for="(message, index) in messages"
                :key="index"
                class="message"
                :class="message.sender === 'user' ? 'user-message' : 'bot-message'"
            >
                <div class="message-content">
                    <span v-if="message.sender === 'user'">{{ message.text }}</span>
                    <span v-else v-html="renderMarkdown(message.text)"></span>
                </div>
            </div>
            <div v-if="loading" class="loading" style="font-size: 14px;color: #999999">
                <span style="position: relative;top: 1.5px;margin-right: 4px;">Generating response</span>
                <v-icon  size="14" color="info">fas fa-spinner fa-pulse</v-icon>
            </div>
        </div>
        <div class="chat-footer">
            <v-text-field
                v-model="userInput"
                placeholder="Type your message..."
                dense
                hide-details
                outlined
                rounded
                @keyup.enter="sendMessage"
            ></v-text-field>
            <v-btn icon @click="sendMessage">
                <v-icon small>fas fa-paper-plane</v-icon>
            </v-btn>
        </div>
    </div>
</div>
</template>

<script>
import MarkdownIt from 'markdown-it';
import {mapState} from "vuex";

const md = new MarkdownIt({
    breaks: true,
});

export default {
    name: "ChatBot",
    data() {
        return {
            isChatOpen: false,
            userInput: '',
            messages: [
                {text: 'Hi there! How can I help you today?', sender: 'bot'},
            ],

            // config
            loading: false
        };
    },
    computed: {
        ...mapState(["user"]),
    },
    methods: {
        toggleChat() {
            this.isChatOpen = !this.isChatOpen;
        },
        sendMessage() {
            if(this.userInput.trim()) {
                this.loading = !this.loading
                // Add user's message to the chat
                this.messages.push({text: this.userInput, sender: 'user'});
                const query = this.userInput;
                this.userInput = '';

                // Call the LLM API with the user's question
                this.chatLLM(query);
            }
        },
        chatLLM(query) {
            this.$api.llm.chat({
                userId: this.user.id,
                query: query,
                chatId: 5
            })
            .then((res) => {
                this.loading = !this.loading
                this.messages.push({
                    text: res.data,
                    sender: 'bot',
                });
            })
            .catch((error) => {
                this.loading = !this.loading
                console.error('Error in chatLLM:', error);
                this.messages.push({
                    text: 'Sorry, there was an error processing your request.',
                    sender: 'bot',
                });
            });
        },

        renderMarkdown(text) {
            return md.render(text);
        },
    },
};
</script>

<style scoped>
.chat-box {
    position: fixed;
    right: 20px;
    bottom: 20px;
}

.chat-box-mobile {
    position: fixed;
    right: 10px;
    bottom: 70px;
}

.chat-window {
    max-height: 500px;
    width: 450px;
    background-color: #fff;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    border-radius: 15px;
    display: flex;
    flex-direction: column;
}

.chat-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    height: 44px;
}

.chat-title {
    font-size: 16px;
    font-weight: bold;
}

.chat-body {
    flex: 1;
    overflow-y: auto;
    padding: 10px;
}

.message {
    margin-bottom: 10px;
    display: flex;
}

.bot-message {
    justify-content: flex-start;
}

.user-message {
    justify-content: flex-end;
}

.message-content {
    overflow-wrap: break-word;
    word-wrap: break-word;
}

.bot-message .message-content {
    color: #000;
    border-radius: 12px;
    padding: 3px 2px;
    max-width: 100%;
    font-size: 14px;
}

.user-message .message-content {
    background-color: rgba(55, 53, 47, 0.04);
    border-radius: 12px;
    padding: 4px 14px;
    max-width: 80%;
    font-size: 14px;
}

.chat-footer {
    display: flex;
    align-items: center;
    padding: 10px;
    border-top: 1px solid #ddd;
}
</style>
