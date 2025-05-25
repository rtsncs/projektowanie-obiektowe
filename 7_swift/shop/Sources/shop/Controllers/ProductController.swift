import Fluent
import Vapor

struct ProductController: RouteCollection {
    func boot(routes: any RoutesBuilder) throws {
        let products = routes.grouped("products")

        products.get(use: self.index)
        products.get("create", use: self.create)
        products.post(use: self.store)
        products.group(":id") { product in
            product.get(use: self.show)
            product.get("edit", use: self.edit)
            product.post("update", use: self.update)
            product.post("delete", use: self.delete)
        }
    }

    func index(req: Request) async throws -> View {
        let products = try await Product.query(on: req.db).all()
        return try await req.view.render("products/index", ["products": products])
    }

    func create(req: Request) async throws -> View {
        let categories = try await Category.query(on: req.db).all()
        return try await req.view.render("products/create", ["categories": categories])
    }

    func store(req: Request) async throws -> Response {
        let product = try req.content.decode(ProductDTO.self).toModel()

        try await product.save(on: req.db)
        return req.redirect(to: "/products")
    }

    func show(req: Request) async throws -> View {
        guard let product = try await Product.find(req.parameters.get("id"), on: req.db) else {
            throw Abort(.notFound)
        }
        try await product.$category.get(on: req.db)
        return try await req.view.render("products/show", ["product": product])
    }

    func edit(req: Request) async throws -> View {
        guard let product = try await Product.find(req.parameters.get("id"), on: req.db) else {
            throw Abort(.notFound)
        }
        let categories = try await Category.query(on: req.db).all()

        struct EditContext: Encodable {
            var product: Product
            var categories: [Category]
        }
        return try await req.view.render("products/edit", EditContext(product: product, categories: categories))
    }

    func update(req: Request) async throws -> Response {
        guard let product = try await Product.find(req.parameters.get("id"), on: req.db) else {
            throw Abort(.notFound)
        }
        let updatedProduct = try req.content.decode(ProductDTO.self)
        product.name = updatedProduct.name
        product.price = updatedProduct.price
        product.$category.id = updatedProduct.category
        try await product.save(on: req.db)
        return req.redirect(to: ".")
    }

    func delete(req: Request) async throws -> Response {
        guard let product = try await Product.find(req.parameters.get("id"), on: req.db) else {
            throw Abort(.notFound)
        }

        try await product.delete(on: req.db)
        return req.redirect(to: "/products")
    }
}
