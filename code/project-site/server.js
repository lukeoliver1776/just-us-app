const express = require("express");

const app = express();
const port = 3000;

app.use(express.static("public"));

app.listen(port, function () {
  console.log("Just Us project site is running at http://localhost:" + port);
});
