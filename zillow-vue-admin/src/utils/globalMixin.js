// globalMixin.js
export default {
    data() {
        return {
            isMobile: window.innerWidth <= 768,
        };
    },
    created() {
        window.addEventListener('resize', this.updateIsMobile);
    },
    beforeUnmount() {
        window.removeEventListener('resize', this.updateIsMobile);
    },
    methods: {
        updateIsMobile() {
            this.isMobile = window.innerWidth <= 768;
        },
    },
};