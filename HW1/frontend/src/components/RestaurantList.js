import React, { useState, useEffect } from 'react';

function RestaurantList() {
  const [restaurants, setRestaurants] = useState([]);
  const [selectedRestaurant, setSelectedRestaurant] = useState(null);
  const [reservationDate, setReservationDate] = useState("");
  const [selectedMeal, setSelectedMeal] = useState(null);
  const [message, setMessage] = useState("");
  const [reservations, setReservations] = useState([]); // Armazenando as reservas

  // Carregar restaurantes
  useEffect(() => {
    fetch("http://localhost:8080/api/restaurants")
      .then((response) => response.json())
      .then((data) => setRestaurants(data));
  }, []);

  // Handle para quando o restaurante for selecionado
  const handleRestaurantClick = (restaurant) => {
    setSelectedRestaurant(restaurant);
  };

  const handleReservation = async (mealId,restaurantName, mealDate) => {
    const response = await fetch(`http://localhost:8080/api/reservations?mealId=${mealId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });

    const data = await response.json();
    if (response.ok) {
      setReservations((prev) => [
        ...prev,
        { mealId, token: data.token, date: mealDate, restaurantName: restaurantName },
      ]);
      setMessage(`Reserva feita com sucesso! Token: ${data.token}`);
    } else {
      setMessage("Erro ao criar reserva.");
    }
};
  const handleDeleteReservation = async (token) => {
  try {
    const response = await fetch(`http://localhost:8080/api/reservations/token/${token}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });

    // Verifique se a resposta foi bem-sucedida
    if (response.ok) {
      // Remover a reserva do estado
      setReservations((prev) => prev.filter((reservation) => reservation.token !== token));
      setMessage("Reserva cancelada com sucesso.");
    } else {
      const errorMessage = await response.text();
      setMessage(`Erro ao cancelar reserva: ${errorMessage}`);
    }
  } catch (error) {
    console.error("Erro ao excluir reserva:", error);
    setMessage("Erro ao cancelar reserva.");
  }
};

  return (
    <div className='main-container'>
      <div className='restaurantcontainer'>
        <h1 className='escolha'>Escolha um Restaurante</h1>
        <div className="restaurant-list">
          {restaurants.map((restaurant) => (
            <div 
              key={restaurant.id} 
              className={`restaurant-item ${selectedRestaurant && selectedRestaurant.id === restaurant.id ? 'selected' : ''}`}
              onClick={() => handleRestaurantClick(restaurant)}
            >
              {restaurant.name}
            </div>
          ))}
        </div>

        {selectedRestaurant && (
          <div className='meal-container'>
            <h2 >{selectedRestaurant.name}</h2>
            <h3>Refeições</h3>
            <div className="meal-list">
              {selectedRestaurant.meals.map((meal) => (
                <div key={meal.id} className="meal-item">
                  <div className='meal-button'>
                    <h4>{meal.name}</h4>
                     {reservations.some((reservation) => reservation.mealId === meal.id) ? (
                    <button disabled className="reserved-btn">Reservado</button>
                  ) : (
                    <button className="reserve-btn" onClick={() => handleReservation(meal.id, selectedRestaurant.name, meal.date)}>
                      Reservar
                    </button>
                  )}
                  </div>
                  <p>{meal.date}</p>
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
      {/* Lista de Reservas ao Lado */}
          <div className="my-reservations">
            <h3 className='reservas-title'>Minhas Reservas</h3>
            {reservations.length > 0 ? (
            <div className="reservations-list">
              {reservations.map((reservation) => (
                <div key={reservation.token} className="reservation-item">
                  <p>{reservation.restaurantName} - {reservation.date}</p>
                   <button
                      className="delete-btn"
                      onClick={() => handleDeleteReservation(reservation.token)}
                    >
                      <i className="fas fa-trash-alt"></i> {/* Ícone de lixeira */}
                    </button>
                </div>
              ))}
            </div>
          ) : (
            <p style={{textAlign:'center'}} >Você não tem reservas.</p>
          )}
          </div>
    </div>
  );
}

export default RestaurantList;
