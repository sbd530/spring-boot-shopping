module.exports = {
  devServer: {
    proxy: {
      "/dashboard": {
        target: "http://localhost:8081",
        changeOrigin: true,
      },
    },
  },
};
