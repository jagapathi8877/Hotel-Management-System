# Hotel Management System

## Complete Java Swing + MySQL Desktop Application

---

## Prerequisites

1. **Java JDK 8+** installed (`java -version` to verify)
2. **MySQL 8.0+** installed and running on `localhost:3306`
3. **MySQL Connector/J** (JDBC driver) — download from:
   https://dev.mysql.com/downloads/connector/j/
   
   Select "Platform Independent" → download the `.zip` → extract → find `mysql-connector-j-X.X.XX.jar`

---

## Setup Instructions

### Step 1: Set up the Database

Open MySQL command-line client or MySQL Workbench and run:

```sql
source C:/SEM_4/AMSD/HotelManagementSystem/hotel_management.sql;
```

Or copy-paste the contents of `hotel_management.sql` directly.

### Step 2: Configure Database Credentials

Open `HotelManagementSystem.java` and edit lines near the top:

```java
static final String DB_URL  = "jdbc:mysql://localhost:3306/hotel_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
static final String DB_USER = "root";
static final String DB_PASS = "";  // <-- Set your MySQL root password here
```

### Step 3: Compile

```bash
javac HotelManagementSystem.java
```

### Step 4: Run

```bash
java -cp ".;mysql-connector-j-X.X.XX.jar" HotelManagementSystem
```

Replace `mysql-connector-j-X.X.XX.jar` with the actual filename of the JDBC driver you downloaded.

**Example:**
```bash
java -cp ".;mysql-connector-j-9.1.0.jar" HotelManagementSystem
```

> On Linux/Mac, use `:` instead of `;` as classpath separator.

---

## Default Login Credentials

| Username    | Password   | Role          |
|-------------|-----------|---------------|
| `admin`     | `admin123` | Admin         |
| `reception` | `recep123` | Receptionist  |

---

## Features

### Authentication & Roles
- Secure login system
- Admin and Receptionist roles
- Role-based access control (Reports = Admin only, Room add/delete = Admin only)

### Room Management
- Add / Edit / Delete rooms
- Room types: Single, Double, Deluxe, Suite
- Price per night
- Real-time status display (Available, Booked, Under Maintenance)
- Filter by status

### Customer Management
- Add / Edit customer profiles
- Stores: Name, Phone, Email, ID Proof (type + number), Address
- Search customers by name, phone, or email
- View booking history per customer

### Booking System
- Create new bookings with date-based availability check
- Automatic total calculation (price × nights)
- Double-booking prevention
- Check-In and Check-Out operations
- Booking cancellation
- Filter by booking status

### Billing & Payments
- Generate invoice for checked-out bookings
- Automatic 18% GST calculation
- Payment modes: Cash, Card, UPI
- Auto-generated receipt numbers
- Printable invoice (Swing print dialog)

### Admin Dashboard
- Total rooms count
- Available rooms count
- Occupied rooms count
- Total revenue
- Today's bookings count
- Recent bookings list

### Reports (Admin Only)
- Customer Report (with booking count and total spent)
- Booking Report (with date range filter)
- Revenue Report (with summary statistics)

---

## Architecture

The application follows MVC-like separation within a single file:
- **Database Configuration** — top of file, easily configurable
- **UI Utilities** — reusable styled component factory methods
- **Screen Panels** — each screen is a self-contained panel builder
- **Data Access** — PreparedStatements throughout (SQL injection safe)
- **Session State** — static fields for logged-in user info
