package com.product;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestor de categorías.
 * Este gestor permite crear y eliminar categorías.
 * @assignment Práctica 1, 2
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version JDK 21 LTS
 * @beta
 */
public class CategoryManager {

    /**
     * Categorías administradas
     * @persistente
     */
    private ArrayList<CategoryEntity> categories;

    /**
     * Constructor de cadenas mutables
     * Ayuda con la eficiencia al escribir varios caracteres
     */
    private StringBuilder sb;

    /**
     * Herramienta para recolectar a entrada del usuario
     */
    private Scanner sc;

    /**
     * Constructor del gestor de categorías.
     */
    public CategoryManager() {
        this.categories = new ArrayList<>();
        this.sb = new StringBuilder();
        this.sc = new Scanner(System.in);
    }

    /**
     * Imprime las categorías activas (status 1).
     * Si no hay categorías activas, muestra un mensaje indicando que no existen.
     */
    public void printActiveCategories() {
        sb.setLength(0);
        ArrayList<CategoryEntity> activeCategories = getActiveCategories();
        
        if (activeCategories.isEmpty())
            System.out.println("No existen categorías registradas");
        else {
            sb.append("[");
            for (int i = 0; i < activeCategories.size(); i++) {
                sb.append(activeCategories.get(i).toString());
                if (i < activeCategories.size() - 1)
                    sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
    }

    /**
     * Regresa una lista con todas categorías
     * @return Una lista con todas categorías
     */
    public ArrayList<CategoryEntity> getCategories() {
        return new ArrayList<CategoryEntity>(categories);
    }
    
    /**
     * Regresa una lista con las categorías activas
     * @return Una lista con las categorías activas
     */
    public ArrayList<CategoryEntity> getActiveCategories() {
        ArrayList<CategoryEntity> activas = new ArrayList<CategoryEntity>(categories);
        activas.removeIf(category -> (category.getStatus() == 0));
        return activas;
    }
    
    /**
     * Regresa un arreglo con todas categorías
     * @return Un arreglo con todas categorías
     */
    public CategoryEntity[] getCategoriesArray() {
        return getCategories().toArray(new CategoryEntity[0]);
    }

    /**
     * Regresa un arreglo con las categorías activas
     * @return Un arreglo con las categorías activas
     */
    public CategoryEntity[] getActiveCategoriesArray() {
        return getActiveCategories().toArray(new CategoryEntity[0]);
    }

    /**
     * Crea una CategoryEntity.
     * El atributo <code>status</code>, por defecto es 1.
     * @param category_id Identificador de la categoría
     * @param category Nombre de la categoría
     * @param tag Etiqueta de la categoría
     * @return <code>true</code> si fue creado con éxito, <code>false</code> si ocurrió un error.
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public boolean createCategory(Integer category_id, String category, String tag) {
        return createCategory(new CategoryEntity(category_id, category, tag));
    }

    /**
     * Crea una nueva categoría.
     * @param newCategory La nueva categoría a crear
     * @return true si la categoría fue creada exitosamente, false si ya existe o es nula
     */
    private boolean createCategory(CategoryEntity newCategory) {
        if (newCategory == null) {
            return false;
        }
        // Verificar unicidad de category_id, category y tag
        for (CategoryEntity existing : categories) {
            if (existing.getCategoryId().equals(newCategory.getCategoryId()) ||
                existing.getCategory().equalsIgnoreCase(newCategory.getCategory()) ||
                existing.getTag().equalsIgnoreCase(newCategory.getTag())) {
                return false;
            }
        }
        categories.add(newCategory);
        return true;
    }

    /**
     * Elimina una categoría por su ID.
     * @param categoryId El ID de la categoría a eliminar
     * @return true si la categoría fue eliminada exitosamente, false si no existe o el ID es nulo
     */
    public boolean deleteCategory(Integer categoryId) {
        if (categoryId == null)
            return false;
        for (CategoryEntity category : categories) {
            if (category.getCategoryId().equals(categoryId)) {
                if (category.getStatus() == 0)
                    throw new IllegalStateException("El intento es fallido, pues la categoría está inactiva");
                category.setStatus(0);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si una cadena es un número entero.
     * @param str La cadena a verificar
     * @return true si es un entero, false en caso contrario
     */
    private boolean isInt(String str) {
        if (str == null || str.isEmpty())
            return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Muestra el menú principal para interactuar con el gestor.
     */
    private void menu() {
        int option = 0;
        do {
            sb.setLength(0);
            sb.append("\nGestor de categorías\n");
            sb.append("1. Crear categoría\n");
            sb.append("2. Eliminar categoría\n");
            sb.append("3. Listar categorías\n");
            sb.append("0. Salir\n");
            sb.append("Seleccione una opción: ");
            System.out.print(sb.toString());
            sb.setLength(0);

            String input = sc.nextLine();
            if (!isInt(input)) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                continue;
            }
            option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    menuCreateCategory();
                    break;
                case 2:
                    menuDeleteCategory();
                    break;
                case 3:
                    printActiveCategories();
                    break;
                case 0:
                    System.out.println("Saliendo del gestor de categorías.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (option != 0);
        sc.close();
    }

    /**
     * Menú para crear una nueva categoría.
     * @throws IllegalArgumentException Si ocurre un error al crear una Categoría
     */
    private void menuCreateCategory() {
        Integer id;
        String category;
        String tag;

        sb.setLength(0);
        sb.append("Crear nueva categoría\n");

        // Validación de ID
        do {
            sb.append("Ingrese el ID de la categoría (número entero positivo): ");
            System.out.print(sb.toString());
            sb.setLength(0);
            String input = sc.nextLine();
            if (!isInt(input) || Integer.parseInt(input) < 0) {
                System.out.println("ID inválido. Debe ser un número entero positivo.");
                continue;
            }
            id = Integer.parseInt(input);
            if (!isIdUnique(id)) {
                System.out.println("ID ya existe. Por favor, ingrese un ID único.");
                continue;
            }
            break;
        } while (true);

        // Validación del Nombre
        do {
            sb.append("Ingrese el nombre de la categoría: ");
            System.out.print(sb.toString());
            sb.setLength(0);
            category = sc.nextLine();
            if (category.isEmpty()) {
                System.out.println("Nombre inválido. No puede estar vacío.");
                continue;
            }
            if (!isCategoryUnique(category)) {
                System.out.println("Nombre ya existe. Por favor, ingrese un nombre único.");
                continue;
            }
            break;
        } while (true);

        // Validación del Tag
        do {
            sb.append("Ingrese el tag de la categoría: ");
            System.out.print(sb.toString());
            sb.setLength(0);
            tag = sc.nextLine();
            if (tag.isEmpty()) {
                System.out.println("Tag inválido. No puede estar vacío.");
                continue;
            }
            if (!isTagUnique(tag)) {
                System.out.println("Tag ya existe. Por favor, ingrese un tag único.");
                continue;
            }
            break;
        } while (true);

        CategoryEntity newCategory = new CategoryEntity(id, category, tag);
        if (createCategory(newCategory)) {
            System.out.println("Categoría creada exitosamente: " + newCategory);
        } else {
            System.out.println("Error al crear la categoría.");
        }
    }

    /**
     * Menú para eliminar una categoría (cambiar su estatus a 0).
     */
    private void menuDeleteCategory() {
        sb.setLength(0);
        sb.append("Eliminar categoría\n");
        sb.append("Ingrese el ID de la categoría: ");
        System.out.print(sb.toString());
        sb.setLength(0);

        String input = sc.nextLine();
        if (!isInt(input)) {
            System.out.println("ID inválido. Debe ser un número entero.");
            return;
        }
        Integer id = Integer.parseInt(input);
        if (deleteCategory(id))
            System.out.println("Categoría eliminada exitosamente.");
        else
            System.out.println("Error: No se encontró una categoría con el ID proporcionado.");
    }

    /**
     * Verifica si un ID es único.
     * @param id El ID a verificar
     * @return true si es único, false si ya existe
     */
    private boolean isIdUnique(Integer id) {
        for (CategoryEntity category : categories) {
            if (category.getCategoryId().equals(id))
                return false;
        }
        return true;
    }

    /**
     * Verifica si un nombre de categoría es único.
     * @param category El nombre a verificar
     * @return true si es único, false si ya existe
     */
    private boolean isCategoryUnique(String category) {
        for (CategoryEntity existing : categories) {
            if (existing.getCategory().equalsIgnoreCase(category))
                return false;
        }
        return true;
    }

    /**
     * Verifica si un tag es único.
     * @param tag El tag a verificar
     * @return true si es único, false si ya existe
     */
    private boolean isTagUnique(String tag) {
        for (CategoryEntity existing : categories) {
            if (existing.getTag().equalsIgnoreCase(tag))
                return false;
        }
        return true;
    }

    /**
     * Método principal para ejecutar el gestor de categorías.
     * @param args Argumentos de la línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        CategoryManager manager = new CategoryManager();
        manager.menu();
    }
}