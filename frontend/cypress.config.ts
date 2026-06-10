import { defineConfig } from "cypress";

export default defineConfig({
  allowCypressEnv: false,
  env: {
    apiURL: process.env.CYPRESS_apiUrl || "http://localhost:8080",
  },

  e2e: {
    baseUrl: "http://localhost:5173",
    setupNodeEvents(on, config) {
      return config;
    },
  },
});
