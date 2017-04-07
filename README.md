# InventoryManagementSystem_GraduateLevel
Team project. Develop a simple inventory management system by using some patterns of enterprise application architecture such as layering, domain model, mapping, gateway, class table inheritance, single table inheritance, concrete table inheritance, lazy load and so on. 

Mapping Inheritance to Relational DB
Introduction
You are building an inventory system for a hardware store.  For this project, you only have to worry about this portion of its object model:

![object model](/image/object_model.jpg)

The important things to notice from this are:

*	We are only worried about tools and fasteners
*	Power tools are a subset of tools, but tools is NOT abstract (i.e. a hammer would be a tool, but a nail gun would be a power tool)
*	There are two types of nails: individual nails and nails that are in a strip to be fed to a power nail gun
*	Power tools know which strip nails work for them and the strip nails know which power tools they can be used in



## Details:
* Build three databases to hold this information; one based on each of the three patterns; which are class table inheritance, single table inheritance, concrete table inheritance 
* Implement domain model strategy for business logic. The classes in the diagram including both a finder constructor and a creation constructor.
* Implement gateway strategy and mapping startegy between the data source layer and domain layer
* Test-driver develoment and test case coverage should be good.
* Implement lazy load strategy when a lot of objects are loaded from database.
* Create presentation layer to manupulate(select, delete, insert and update) the database


