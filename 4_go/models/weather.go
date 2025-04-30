package models

import "gorm.io/gorm"

type Weather struct {
	gorm.Model
	City string
	Temp float64
	FeelsLike float64
	Pressure int
	Humidity int
	Condition string
	Description string
}

