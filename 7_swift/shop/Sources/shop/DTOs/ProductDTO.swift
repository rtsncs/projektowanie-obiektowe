import Vapor
import struct Foundation.UUID

struct ProductDTO: Content {
    var id: UUID?
    var name: String
    var price: Double
    var category: Category.IDValue

    func toModel() -> Product {
        return Product(id: self.id, name: self.name, price: self.price, categoryID: self.category)
    }
}
