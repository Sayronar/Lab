package ru.sayron.client.utility;

import ru.sayron.client.Main;
import ru.sayron.common.exceptions.*;
import ru.sayron.common.data.*;
import ru.sayron.common.utility.Outputer;

import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Asks a user a organization's value.
 */
public class OrganizationAsker {
    private final Long MIN_EMPLOYEES = 1L;
    private final int MIN_TURNOVER = 1;

    private Scanner userScanner;
    private boolean fileMode;

    public OrganizationAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Sets a scanner to scan user input.
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets organization asker mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets organization asker mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Outputer.println("Введите название организации:");
                Outputer.print(Main.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Название организации не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Outputer.printerror("Название организации не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    public long askCoordinatesX() throws IncorrectInputInScriptException {
        String strX;
        long x;
        while (true) {
            try {
                Outputer.println("Введите координату X:");
                Outputer.print(Main.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    public Integer askCoordinatesY() throws IncorrectInputInScriptException {
        String strY;
        Integer y;
        while (true) {
            try {
                Outputer.println("Введите координату Y:");
                Outputer.print(Main.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strY);
                y = Integer.parseInt(strY);
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        long x;
        Integer y;
        x = askCoordinatesX();
        y = askCoordinatesY();
        return new Coordinates(x, y);
    }

    public int askTurnover() throws IncorrectInputInScriptException {
        String strTurnover;
        int turnover;
        while (true) {
            try {
                Outputer.println("Введите годовой оборот:");
                Outputer.print(Main.PS2);
                strTurnover = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strTurnover);
                turnover = Integer.parseInt(strTurnover);
                if (turnover < MIN_TURNOVER) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Годовой оборот не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Outputer.printerror("Годовой оборот должен быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Годовой оборот должен быть представлен числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return turnover;
    }

    public String askFullName() throws IncorrectInputInScriptException {
        String fullName;
        while (true) {
            try {
                Outputer.println("Введите полное название компании:");
                Outputer.print(Main.PS2);
                fullName = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(fullName);
                if (fullName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Полное название компании не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Outputer.printerror("Полное название компании не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return fullName;
    }

    public Long askEmployeesCount() throws IncorrectInputInScriptException {
        String strEmployeesCount;
        Long employeesCount;
        while (true) {
            try {
                Outputer.println("Введите количество сотрудников Y:");
                Outputer.print(Main.PS2);
                strEmployeesCount = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strEmployeesCount);
                employeesCount = Long.parseLong(strEmployeesCount);
                if (employeesCount < MIN_EMPLOYEES) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Количество сотрудников не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Outputer.printerror("Количество сотрудников должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Количество сотрудников должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return employeesCount;
    }

    public OrganizationType askType() throws IncorrectInputInScriptException {
        String strType;
        OrganizationType type;
        while (true) {
            try {
                Outputer.println("Список типов организаций - " + OrganizationType.nameList());
                Outputer.println("Введите тип:");
                Outputer.print(Main.PS2);
                strType = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strType);
                type = OrganizationType.valueOf(strType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Тип не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Outputer.printerror("Типа нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return type;
    }

    public String askStreet() throws IncorrectInputInScriptException {
        String StreetName;
        while (true) {
            try {
                Outputer.println("Введите улицу:");
                Outputer.print(Main.PS2);
                StreetName = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(StreetName);
                if (StreetName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Улица не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Outputer.printerror("Улица не может быть пустой!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return StreetName;
    }

    public Address askAddress() throws IncorrectInputInScriptException {
        String street;
        Location town;
        street = askStreet();
        town = askLocation();
        return new Address(street, town);
    }

    public int askLocationX() throws IncorrectInputInScriptException {
        String strX;
        int x;
        while (true) {
            try {
                Outputer.println("Введите координату X:");
                Outputer.print(Main.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strX);
                x = Integer.parseInt(strX);
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    public Float askLocationY() throws IncorrectInputInScriptException {
        String strY;
        Float y;
        while (true) {
            try {
                Outputer.println("Введите координату Y:");
                Outputer.print(Main.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strY);
                y = Float.parseFloat(strY);
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }


    public long askLocationZ() throws IncorrectInputInScriptException {
        String strZ;
        long z;
        while (true) {
            try {
                Outputer.println("Введите координату Z:");
                Outputer.print(Main.PS2);
                strZ = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strZ);
                z = Long.parseLong(strZ);
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Координата Z не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Координата Z должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return z;
    }

    public Location askLocation() throws IncorrectInputInScriptException {
        int x;
        Float y; //Поле не может быть null
        long z;
        x = askLocationX();
        y = askLocationY();
        z = askLocationZ();
        return new Location(x, y, z);
    }

    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Outputer.println(finalQuestion);
                Outputer.print(Main.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Outputer.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString() {
        return "OrganizationAsker (вспомогательный класс для запросов пользователю)";
    }
}