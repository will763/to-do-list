import ToDoList from "./components/ToDoList"

function App() {

  return (
    <div
     className="min-h-screen max-w-full dark:bg-mobile-dark sm:dark:bg-desktop-dark
      bg-mobile-light sm:bg-desktop-light bg-[length:100%_12.2rem] md:bg-[length:100%_13.2rem]
      bg-no-repeat flex justify-center dark:bg-[#161620] bg-[#e4e5f1]
       text-white font-josefin-sans transition-colors duration-300 "
    >
      <ToDoList />
    </div>
  )
}

export default App