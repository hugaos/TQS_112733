**1-a)**
```
assertThat(found).isEqualTo(alex);

assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());

assertThat(results).hasSize(2).extracting(Employee::getEmail).containsExactlyInAnyOrder("bob@ua.pt","ron@ua.pt");
```

---
**1-c)**
```
@BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```
---
**1-d)**

- A anotação **@Mock** (Mockito) é destinada a testes unitários sem usar o contexto do Sring.
- A anotação **@MockBean** é usada para testes de integração, onde o Spring Boot é carregado.

---

**1-e)**

O ficheiro `application-integrationtest.properties` define configurações específicas para testes de integração no Spring Boot. Permite isolar o ambiente de teste, configurando bases de dados, serviços externos ou ajustes necessários sem afetar o desenvolvimento do projeto.

---

**1-f)**

- **C** - `C_EmployeeController_WithMockServiceTest`

Esta estratégia testa apenas a camada de controlador, isolando-a do serviço e da base de dados. Utiliza `@WebMvcTest(EmployeeRestController.class)` para carregar um ambiente web e `@MockBean` para simular o comportamento do serviço. O *MockMvc* é usado para enviar pedidos HTTP e validar respostas.

- **D** - `D_EmployeeRestControllerIT`

Testa-se a API completa incluindo controlador, serviço e repositório, mas num ambiente simulado. Usa `@SpringBootTest(webEnvironment = WebEnvironment.MOCK)` para carregar o contexto Spring Boot. O *MockMvc* permite interagir com a API como se fosse um cliente real. 

- **E** - `E_EmployeeRestControllerTemplateIT`

Testa a API num ambiente real, iniciando um servidor HTTP (`@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)`) e usa `TestRestTemplate` para fazer pedidos. Permite validar a integração total do sistema, incluindo a base de dados se configurada.