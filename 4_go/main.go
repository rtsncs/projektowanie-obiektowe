package main

import (
	"weather/controllers"
	"weather/database"

	"github.com/labstack/echo/v4"
)

func main() {
	database.InitDatabase()

	e := echo.New()
	e.POST("/weather", controllers.GetWeather)
	e.GET("/weather", controllers.GetWeather)

	e.Logger.Fatal(e.Start(":8080"))
}
