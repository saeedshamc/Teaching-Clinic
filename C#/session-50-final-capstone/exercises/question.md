# تمرین نهایی — Final Capstone

## پروژه: Clinic Management System

### بخش ۱ — Domain (الزامی)

Entity های `Patient`، `Doctor`، `Appointment` با validation

---

### بخش ۲ — Service Layer (الزامی)

- `PatientService`: Register, GetById, GetAll
- `AppointmentService`: Book, Cancel, GetByPatient
- Business rules: no double booking, no past appointments

---

### بخش ۳ — API (الزامی)

REST endpoints با status codes صحیح:
- `POST /api/patients`
- `GET /api/patients/{id}`
- `POST /api/appointments`
- `GET /api/appointments?patientId=`

---

### بخش ۴ — Bonus

- [ ] JWT Authentication
- [ ] Unit Tests (xUnit)
- [ ] Docker + docker-compose
- [ ] GitHub Actions CI
- [ ] Deploy to Azure/Railway

---

### معیار ارزیابی

| معیار | امتیاز |
|-------|--------|
| Layered architecture | 20% |
| SOLID principles | 15% |
| REST API design | 15% |
| Validation + Error handling | 15% |
| Code quality + naming | 15% |
| Documentation (README) | 10% |
| Bonus features | 10% |

---

**راهنما:** [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)

**موفق باشید!** 🎓
