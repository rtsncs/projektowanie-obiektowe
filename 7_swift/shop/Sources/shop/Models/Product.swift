import Fluent
import Vapor
import struct Foundation.UUID

/// Property wrappers interact poorly with `Sendable` checking, causing a warning for the `@ID` property
/// It is recommended you write your model with sendability checking on and then suppress the warning
/// afterwards with `@unchecked Sendable`.
final class Product: Model, Content, @unchecked Sendable {
    static let schema = "products"

    @ID(key: .id)
    var id: UUID?

    @Field(key: "name")
    var name: String

    @Field(key: "price")
    var price: Double

    @Parent(key: "category_id")
    var category: Category

    init() { }

    init(id: UUID? = nil, name: String, price: Double, categoryID: Category.IDValue) {
        self.id = id
        self.name = name
        self.price = price
        self.$category.id = categoryID
    }

    func toDTO() -> ProductDTO {
        .init(
            id: self.id,
            name: self.name,
            price: self.price,
            category: self.$category.id
        )
    }
}
