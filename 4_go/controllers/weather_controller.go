package controllers

import (
	"fmt"
	"strings"
	"net/http"
	"weather/database"
	"weather/models"
	"weather/proxy"
	"github.com/labstack/echo/v4"
)

type Request struct {
	Cities []string `json:"cities"`
}

func GetWeather(c echo.Context) error {
	var req Request
	var cities []string
	if err := c.Bind(&req); err != nil || len(req.Cities) == 0 {
		query := c.QueryParam("cities")
		if query == "" {
			return c.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid request"})
		}
		parts := strings.Split(query, ",")
		for _, part := range parts {
			trimmed := strings.TrimSpace(part)
			if trimmed != "" {
				fmt.Print(trimmed)
				cities = append(cities, trimmed)
			}
		}
	} else {
		cities = req.Cities
	}

	var result []models.Weather
	for _, city := range cities {
		var weatherData models.Weather
		if err := database.DB.Where("city = ?", city).First(&weatherData).Error; err == nil {
			result = append(result, weatherData)
			break
		}

		response, err := proxy.FetchWeather(city)
		if err != nil {
			fmt.Printf("Error: %s\n", err)
			return c.JSON(http.StatusInternalServerError, map[string]string{"error": "Failed to fetch weather data"})
		}

		newWeatherData := models.Weather{
			City:        response.Name,
			Temp: response.Main.Temp,
			FeelsLike: response.Main.FeelsLike,
			Pressure: response.Main.Pressure,
			Humidity: response.Main.Humidity,
			Condition: response.Weather[0].Main,
			Description: response.Weather[0].Description,
		}
		database.DB.Create(&newWeatherData)
		result = append(result, newWeatherData)
	}

	return c.JSON(http.StatusOK, result)
}
