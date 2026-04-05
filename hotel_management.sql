-- ============================================================
-- HOTEL MANAGEMENT SYSTEM v2.0 - DATABASE SETUP SCRIPT
-- MySQL 8.0+
-- Enhanced schema with audit logs, notifications support
-- ============================================================

DROP DATABASE IF EXISTS hotel_management;
CREATE DATABASE hotel_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hotel_management;

-- ============================================================
-- TABLE: users
-- ============================================================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Receptionist') NOT NULL DEFAULT 'Receptionist',
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ============================================================
-- TABLE: rooms
-- ============================================================
CREATE TABLE rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(10) NOT NULL UNIQUE,
    room_type ENUM('Single', 'Double', 'Deluxe', 'Suite') NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL,
    status ENUM('Available', 'Booked', 'Under Maintenance') NOT NULL DEFAULT 'Available',
    floor INT NOT NULL DEFAULT 1,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ============================================================
-- TABLE: customers
-- ============================================================
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    id_proof_type ENUM('Aadhar', 'Passport', 'Driving License', 'Voter ID', 'PAN Card') NOT NULL,
    id_proof_number VARCHAR(50) NOT NULL,
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ============================================================
-- TABLE: bookings
-- ============================================================
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    actual_check_in DATETIME NULL,
    actual_check_out DATETIME NULL,
    status ENUM('Reserved', 'Checked-In', 'Checked-Out', 'Cancelled') NOT NULL DEFAULT 'Reserved',
    num_guests INT NOT NULL DEFAULT 1,
    total_amount DECIMAL(10, 2) NOT NULL,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON UPDATE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON UPDATE CASCADE,
    FOREIGN KEY (created_by) REFERENCES users(id) ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- TABLE: payments
-- ============================================================
CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    gst_amount DECIMAL(10, 2) NOT NULL,
    total_with_tax DECIMAL(10, 2) NOT NULL,
    payment_mode ENUM('Cash', 'Card', 'UPI') NOT NULL,
    receipt_number VARCHAR(20) NOT NULL UNIQUE,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES bookings(id) ON UPDATE CASCADE
) ENGINE=InnoDB;

-- ============================================================
-- TABLE: audit_logs (NEW - Enterprise feature)
-- ============================================================
CREATE TABLE audit_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    action VARCHAR(100) NOT NULL,
    entity_type VARCHAR(50),
    entity_id INT,
    details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- ============================================================
-- SAMPLE DATA
-- ============================================================

-- Users (plaintext passwords for demo: admin123, recep123)
INSERT INTO users (username, password, role, full_name) VALUES
('admin',     'admin123', 'Admin',        'System Administrator'),
('reception', 'recep123', 'Receptionist', 'Front Desk Staff');

-- Rooms
INSERT INTO rooms (room_number, room_type, price_per_night, status, floor, description) VALUES
('101', 'Single', 1500.00, 'Available',         1, 'Cozy single room with city view'),
('102', 'Single', 1500.00, 'Available',         1, 'Single room with garden view'),
('103', 'Single', 1800.00, 'Available',         1, 'Premium single room with workspace'),
('201', 'Double', 2500.00, 'Available',         2, 'Spacious double room'),
('202', 'Double', 2500.00, 'Available',         2, 'Double room with balcony'),
('203', 'Double', 2800.00, 'Available',         2, 'Double room with lounge area'),
('301', 'Deluxe', 4000.00, 'Available',         3, 'Deluxe room with premium amenities'),
('302', 'Deluxe', 4000.00, 'Under Maintenance', 3, 'Deluxe room under renovation'),
('303', 'Deluxe', 4500.00, 'Available',         3, 'Deluxe room with jacuzzi'),
('401', 'Suite',  7500.00, 'Available',         4, 'Royal suite with living area'),
('402', 'Suite',  7500.00, 'Available',         4, 'Presidential suite with city panorama'),
('403', 'Suite',  9000.00, 'Available',         4, 'Grand suite with private terrace');



SELECT 'Database v2.0 setup completed successfully!' AS Status;
