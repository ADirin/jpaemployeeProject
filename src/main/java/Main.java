import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // Create EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

        // Create EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Begin transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // Create some projects
            Project project1 = new Project("Project A", 100000);
            Project project2 = new Project("Project B", 150000);

            // Persist the projects
            entityManager.persist(project1);
            entityManager.persist(project2);

            // Create an employee
            Employee employee = new Employee("John", "Doe", 50000);

            // Assign projects to the employee
            employee.getProjects().add(project1);
            employee.getProjects().add(project2);

            // Persist the employee
            entityManager.persist(employee);

            // Commit transaction
            transaction.commit();

            // Display success message
            System.out.println("Employee and projects saved successfully.");
        } catch (Exception e) {
            // Rollback transaction if there's an exception
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close EntityManager
            entityManager.close();
        }

        // Close EntityManagerFactory
        entityManagerFactory.close();
    }
}
