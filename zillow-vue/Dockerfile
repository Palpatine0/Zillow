# Use the node image from official Docker Hub
FROM node:14.5.0-alpine as build-stage

# Set the working directory
WORKDIR /app

# Copy the working directory in the container
COPY package*.json ./

# Install the project dependencies
# if you npm then npm install
RUN yarn install

# Copy the rest of the project files to the container
COPY . .

# Build the Vue.js application to the production mode to dist folder
# here also if you use npm then npm run build
RUN yarn build

# Use the lightweight Nginx image from the previous state to the Nginx container
FROM nginx:stable-alpine as production-stage

RUN rm /etc/nginx/conf.d/default.conf || true

# Copy the build application from the previous state to the Nginx container
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Copy the Nginx configuration file
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

# Expose the port 80 (for simplicity)
EXPOSE 80

# Start nginx to serve the application
CMD ["nginx", "-g", "daemon off;"]