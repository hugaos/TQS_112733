import React, { useState, useEffect } from 'react';
import '../App.css';

function UserView() {
  const [restaurants, setRestaurants] = useState([]);
const [selectedRestaurant, setSelectedRestaurant] = useState(1);
  const [meals, setMeals] = useState([]);
  const [reservations, setReservations] = useState([]);
  const [activeTab, setActiveTab] = useState('restaurants');
  const [weatherData, setWeatherData] = useState(null);
  const [loading, setLoading] = useState(true);

  // Carregar dados iniciais
  useEffect(() => {
    const fetchInitialData = async () => {
      try {
        // Buscar dados em paralelo
        const [restaurantsRes, reservationsRes, weatherRes] = await Promise.all([
          fetch('http://localhost:8080/api/restaurants'),
          fetch('http://localhost:8080/api/reservations'),
          fetch('http://localhost:8080/api/weather')
        ]);

        const restaurantsData = await restaurantsRes.json();
        const reservationsData = await reservationsRes.json();
        const weatherData = await weatherRes.json();

        setRestaurants(restaurantsData);
        setReservations(reservationsData);
        setWeatherData(weatherData);
      } catch (error) {
        console.error("Error fetching data:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchInitialData();
  }, []);

  // Carregar refeições quando selecionar restaurante
  useEffect(() => {
    if (selectedRestaurant) {
      fetch(`http://localhost:8080/api/${selectedRestaurant}/meals`)
        .then(res => res.json())
        .then(data => setMeals(data));
    }
  }, [selectedRestaurant]);

  const getWeatherForDate = (dateString) => {
    if (!weatherData) return null;
    
    const date = new Date(dateString);
    const dateStr = date.toISOString().split('T')[0];
    
    return weatherData.days.find(day => day.datetime === dateStr);
  };

  const handleReservation = async (mealId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/reservations?mealId=${mealId}`, {
        method: "POST"
      });
      
      if (response.ok) {
        const newReservation = await response.json();
        setReservations([...reservations, newReservation]);
      }
    } catch (error) {
      console.error("Erro ao criar reserva:", error);
    }
  };

  const deleteReservation = async (token) => {
    try {
      const response = await fetch(`http://localhost:8080/api/reservations/token/${token}`, {
        method: "DELETE"
      });

      if (response.ok) {
        setReservations(reservations.filter(reservation => reservation.token !== token));
      }
    } catch (error) {
      console.error("Erro ao cancelar reserva:", error);
    }
  };
  const getMealNameById = (mealId) => {
    const meal = meals.find(meal => meal.id === mealId);
    if (meal) {
        return meal.name;
    } else {
        return 'Refeição não especificada';
    }
};

  if (loading) return <div className="loading">Carregando dados...</div>;

  return (
    <div className="user-view">
      <div className="tabs">
        <button 
          className={activeTab === 'restaurants' ? 'active' : ''}
          onClick={() => setActiveTab('restaurants')}
        >
          Restaurantes
        </button>
        <button
          className={activeTab === 'reservations' ? 'active' : ''}
          onClick={() => setActiveTab('reservations')}
        >
          Minhas Reservas
        </button>
      </div>

      {activeTab === 'restaurants' ? (
        <div className="restaurant-section">
          <h2>Escolha um Restaurante</h2>
          <div className="restaurant-list">
            {restaurants.map(restaurant => {

              return (
                <div 
                  key={restaurant.id}
                  className={`restaurant-card ${selectedRestaurant === restaurant.id ? 'selected' : ''}`}
                  onClick={() => setSelectedRestaurant(restaurant.id)}
                >
                  <div className="restaurant-header">
                    <h3>{restaurant.name}</h3>

                  </div>
                </div>
              );
            })}
          </div>

          {selectedRestaurant && (
            <div className="meal-section">
              <h3>Refeições Disponíveis</h3>
              <div className="meal-list">
                {meals.map(meal => {
                const weather = getWeatherForDate(meal.date);
                const isReserved = reservations.some(r => r.mealId === meal.id);
                
                return (
                    <div key={meal.id} className={`meal-card-horizontal ${isReserved ? 'reserved' : ''}`}>
                        <div className="meal-header">
                            <h4>{meal.name}</h4>
                            <p className="meal-date">{new Date(meal.date).toLocaleDateString('pt-PT')}</p>
                        </div>
                        
                        <div className="meal-content">
                            {weather && (
                            <div className="weather-info">
                                <div className="weather-temp">
                                    <span className="temp-current">{Math.round(weather.temp)}°F</span>
                                    <div className="temp-minmax">
                                        <span className="temp-max">↑{Math.round(weather.tempmax)}°F</span>
                                        <span className="temp-min">↓{Math.round(weather.tempmin)}°F</span>
                                    </div>
                                </div>
                                <div className="weather-visual">
                                    <img 
                                        src={`https://raw.githubusercontent.com/visualcrossing/WeatherIcons/main/PNG/2nd%20Set%20-%20Color/${weather.icon}.png`} 
                                        alt={weather.conditions}
                                        className="weather-icon"
                                    />
                                    <span className="weather-conditions">{weather.conditions}</span>
                                </div>
                            </div>
                            )}
                            
                            <button 
                                onClick={() => handleReservation(meal.id)}
                                disabled={isReserved}
                                className={`reserve-btn ${isReserved ? 'disabled' : 'primary-btn'}`}
                            >
                                {isReserved ? 'Reservado' : 'Reservar'}
                            </button>
                        </div>
                    </div>
                );
            })}
              </div>
            </div>
          )}
        </div>
      ) : (
        <div className="reservation-section">
          <h2>Minhas Reservas</h2>
          {reservations.length > 0 ? (
            <div className="reservation-list">
              {reservations.map(reservation => (
                <div key={reservation.token} className="reservation-card">
                  <div className="reservation-info">
                    <p><strong>Código:</strong> {reservation.token}</p>
                    <p><strong>Refeição:</strong> {getMealNameById(reservation.mealId)}</p>
                    <p><strong>Data:</strong> {new Date(reservation.timestamp).toLocaleString('pt-PT')}</p>
                  </div>
                  <button 
                    onClick={() => deleteReservation(reservation.token)}
                    className="cancel-btn"
                  >
                    Cancelar
                  </button>
                </div>
              ))}
            </div>
          ) : (
            <p className="no-reservations">Nenhuma reserva encontrada</p>
          )}
        </div>
      )}
    </div>
  );
}

export default UserView;