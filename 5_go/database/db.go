package database

import (
	"gorm.io/gorm"
	"gorm.io/driver/sqlite"
	"weather/models"
)

var DB *gorm.DB

func InitDatabase() {
	var err error
	DB, err = gorm.Open(sqlite.Open("weather.db"), &gorm.Config{})
	if err != nil {
		panic("Failed to connect database")
	}

	DB.AutoMigrate(&models.Weather{})
}
