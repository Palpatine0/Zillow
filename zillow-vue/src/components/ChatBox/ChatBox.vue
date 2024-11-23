<template>
<div :class="!isMobile? 'chat-box':'chat-box-mobile'">
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
                    <span>{{ message.text }}</span>
                </div>
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
export default {
    data() {
        return {
            isChatOpen: false,
            userInput: '',
            messages: [
                { text: 'Hi there! How can I help you today?', sender: 'bot' },
            ],
        };
    },
    computed: {
        isMobile() {
            return window.innerWidth <= 768;
        },
    },
    methods: {
        toggleChat() {
            this.isChatOpen = !this.isChatOpen;
        },
        sendMessage() {
            if (this.userInput.trim()) {
                this.messages.push({ text: this.userInput, sender: 'user' });
                this.userInput = '';
                // Simulate bot response
                setTimeout(() => {
                    this.messages.push({
                        text: "I'm here to help! What can I do for you?",
                        sender: 'bot',
                    });
                }, 1000);
            }
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
    font-weight: bold;
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
