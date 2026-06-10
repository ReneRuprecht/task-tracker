describe("The Task Board Page", () => {
  const projectName = "Cypress Project";
  const taskAddMoreUnitTests = "Add more unit tests";
  const taskAddMoreIntegrationTests = "Add more integration tests";

  before(() => {
    cy.env(["apiUrl"]).then(({ apiUrl }) => {
      cy.request("POST", `${apiUrl}/test/reset`)
        .its("status")
        .should("eq", 200);
    });
  });

  beforeEach(() => {
    cy.visit("/");
  });

  it("should load task board page", () => {
    cy.contains("0% complete").should("be.visible");

    cy.contains("button", "Select Project").should("be.visible");

    cy.contains(/open/i).should("be.visible");
    cy.contains(/closed/i).should("be.visible");

    cy.contains("button", /new/i).should("be.visible");
  });

  it("should create new project", () => {
    cy.intercept("POST", "/api/v1/projects").as("createProject");

    cy.contains("button", /Select Project/i).click();

    cy.get('input[placeholder="new Project"]')
      .should("be.visible")
      .type("Cypress Project{enter}");

    cy.wait("@createProject");

    cy.contains("button", "Cypress Project").should("be.visible");
  });

  it("should create new task via save button", () => {
    cy.intercept("POST", "/api/v1/tasks").as("createTask");

    cy.contains("button", "Select Project").click();
    cy.contains(projectName).click();

    cy.contains("button", "new").click();

    cy.get('input[placeholder="New task..."]')
      .should("be.visible")
      .type(taskAddMoreUnitTests);

    cy.contains("button", /save/i).click();

    cy.wait("@createTask");

    cy.contains(taskAddMoreUnitTests).should("be.visible");
  });

  it("should create new task via enter key", () => {
    cy.intercept("POST", "/api/v1/tasks").as("createTask");

    cy.contains("button", "Select Project").click();
    cy.contains(projectName).click();

    cy.contains("button", "new").click();

    cy.get('input[placeholder="New task..."]')
      .should("be.visible")
      .type("Add more integration tests{enter}");

    cy.wait("@createTask");

    cy.contains(taskAddMoreUnitTests)
      .parent()
      .find("div")
      .should("have.class", "bg-yellow-500")
      .should("be.visible");
  });

  it("should update task status from open to closed", () => {
    cy.intercept("PATCH", "/api/v1/tasks/***").as("patchTask");

    cy.contains("div", "Select Project").click();
    cy.contains(projectName).click();

    cy.contains('[role="button"]', taskAddMoreUnitTests)
      .trigger("mouseenter")
      .click("right");

    cy.wait("@patchTask");

    cy.get('[role="button"]')
      .find("div")
      .should("have.class", "bg-green-500")
      .should("be.visible");
  });

  it("should update task status from closed to open", () => {
    cy.intercept("PATCH", "/api/v1/tasks/***").as("patchTask");

    cy.contains("div", "Select Project").click();
    cy.contains(projectName).click();

    cy.contains('[role="button"]', taskAddMoreUnitTests)
      .trigger("mouseenter")
      .click("right");

    cy.wait("@patchTask");

    cy.get('[role="button"]')
      .find("div")
      .should("have.class", "bg-yellow-500")
      .should("be.visible");
  });

  it("should display 50% completion state", () => {
    cy.intercept("PATCH", "/api/v1/tasks/***").as("patchTask");

    cy.contains("div", "Select Project").click();
    cy.contains(projectName).click();

    cy.contains('[role="button"]', taskAddMoreUnitTests)
      .trigger("mouseenter")
      .click("right");

    cy.wait("@patchTask");

    cy.contains("50% complete").should("be.visible");
  });

  it("should show project as completed (100%) when all tasks are done", () => {
    cy.intercept("PATCH", "/api/v1/tasks/***").as("patchTask");

    cy.contains("div", "Select Project").click();
    cy.contains(projectName).click();

    cy.contains('[role="button"]', taskAddMoreIntegrationTests)
      .trigger("mouseenter")
      .click("right");

    cy.wait("@patchTask");

    cy.contains("100% complete").should("be.visible");
    cy.get('[role="progressbar"]').should("have.class", "bg-green-500");
  });
});
