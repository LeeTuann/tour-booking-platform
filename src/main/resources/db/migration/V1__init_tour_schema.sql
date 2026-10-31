
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255),
                       phone VARCHAR(50),
                       role VARCHAR(50),
                       status VARCHAR(50),
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE tours (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       slug VARCHAR(255) UNIQUE NOT NULL,
                       description TEXT,
                       tour_type VARCHAR(50),
                       departure_location VARCHAR(255),
                       duration_days INT,
                       duration_nights INT,
                       base_price DECIMAL(15, 2),
                       status VARCHAR(50),
                       inclusions TEXT,
                       exclusions TEXT,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE tour_itineraries (
                                  id BIGSERIAL PRIMARY KEY,
                                  tour_id BIGINT NOT NULL REFERENCES tours(id),
                                  day_number INT,
                                  title VARCHAR(255),
                                  description TEXT,
                                  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE tour_schedules (
                                id BIGSERIAL PRIMARY KEY,
                                tour_id BIGINT NOT NULL REFERENCES tours(id),
                                departure_date DATE,
                                return_date DATE,
                                adult_price DECIMAL(15, 2),
                                child_price DECIMAL(15, 2),
                                infant_price DECIMAL(15, 2),
                                max_capacity INT NOT NULL,
                                booked_seats INT DEFAULT 0,
                                held_seats INT DEFAULT 0,
                                status VARCHAR(50),
                                version INT DEFAULT 0, -- Dùng cho Optimistic Lock
                                created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          booking_code VARCHAR(100) UNIQUE NOT NULL,
                          user_id BIGINT NOT NULL REFERENCES users(id),
                          schedule_id BIGINT NOT NULL REFERENCES tour_schedules(id),
                          contact_name VARCHAR(255),
                          contact_email VARCHAR(255),
                          contact_phone VARCHAR(50),
                          total_adults INT DEFAULT 1,
                          total_children INT DEFAULT 0,
                          total_infants INT DEFAULT 0,
                          total_amount DECIMAL(15, 2),
                          status VARCHAR(50),
                          payment_deadline TIMESTAMP WITH TIME ZONE,
                          created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE booking_passengers (
                                    id BIGSERIAL PRIMARY KEY,
                                    booking_id BIGINT NOT NULL REFERENCES bookings(id),
                                    full_name VARCHAR(255),
                                    passenger_type VARCHAR(50),
                                    id_card_number VARCHAR(100),
                                    date_of_birth DATE,
                                    gender VARCHAR(20),
                                    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          booking_id BIGINT NOT NULL REFERENCES bookings(id),
                          transaction_id VARCHAR(100) UNIQUE,
                          payment_method VARCHAR(50),
                          amount DECIMAL(15, 2),
                          status VARCHAR(50),
                          payment_time TIMESTAMP WITH TIME ZONE,
                          created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP WITH TIME ZONE
);
CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL REFERENCES users(id),
                         tour_id BIGINT NOT NULL REFERENCES tours(id),
                         rating INT CHECK (rating >= 1 AND rating <= 5),
                         comment TEXT,
                         created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP WITH TIME ZONE
);