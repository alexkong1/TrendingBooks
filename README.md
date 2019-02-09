### Trending Books

In this exercise, you will create an app that consumes a paginated feed of books and displays them to the user.  The app must display the books in a list, sort them according to their attributes, and present an expanded view of the book's information when a user taps on an item in the list.  The app must also present a 'favorite' toggle on the detail view for each book, whose state must be persisted locally after the application is terminated.

While no storyboards/redlines/mockups have been provided, the application UI should not look subjectively bad.  Take care to provide balance/adequate margins, legible text sizing, etc and to stick to standard platform design patterns where applicable.  Use your judgement.

When writing your solution choose whatever languages, frameworks, and patterns you would use in your own production code.

Your solution must build from source and run on our machines/test devices.

An Android Studio project has been provided to work in.

## UI Requirements
The app consists of two screens: a List Screen displays the entire list of books, and a Detail Screen shows detailed information for each book.  The app should support both orientations without losing state data or becoming unusable.  The Detail Screen does *not* have to be shown along side the List Screen in Landscape.

# The List Screen
The List screen must present a searchable list of the books in a specified sorting order.
Each list item must present the following information:
  1) Book title
  1) Author Last Name
  1) An optional message from the Marketing Department
  1) The book synopsis, shortened as desired, to keep the list looking presentable
  1) An icon on the rightmost end of the list item, if the book has been marked as 'favorited' by the user.  Choose any icon you like.

The List screen must also present a control underneath the app bar allowing a user to sort the list in the following ways:
  1) 'Trending': This is the order of the items, as loaded from the JSON
  1) 'Promoted': Items with a marketing message should be shown before all items without one.  Items should otherwise be ordered alphabetically by title
  1) Author Last Name
  1) Book Title

# The Detail Screen
The Detail screen must present detailed information about a particular book.
It must present the following information:
  1) Book title
  1) Author Full Name
  1) An optional message from the Marketing Department
  1) The book synopsis, no matter the length

Additionally, it must present a 'Favorite' button in the Action menu, which a user may toggle by tapping.  The 'Favorite' button's state must indicate whether or not the book has been favorited.  A user's favorite books must be saved between sessions.

## Technical Docs
# Files
The list of books is contained in a set of files within the `assets` folder of the app module. Each file represents one page of a multi-page data set.  The files are named `trending-books-[page]`, where `page` starts at 1 and could be unboundedly large.

# Data Structures
The books are represented by JSON with the following structure:
```
{
  "id": "...",
  "title": "...",
  "author_firstname": "...",
  "author_lastname": "...",
  "marketing_message": "...",
  "synopsis": "..."
}
```
All fields except `marketing_message` are non-null.  `marketing_message` is nullable.
