import React, { memo, useEffect, useMemo, useState } from "react";

interface Props {
  handleShowItems: (data:[]) => void;
  data: any;
}

function FilterList({handleShowItems,data}: Props) {
    const [filterActive, setFilterActive] = useState<string>('All');

    const selected = useMemo(()=> 'text-blue-700 pointer-events-none', []);
    const normalItem = useMemo(()=> 'hover:text-[#484b6a] dark:hover:text-[#fafafa] cursor-pointer', []);


    function showCompletedItems() {
      const newArray = data?.filter((data:any) => {
        return data.completed === true;
      })
      handleShowItems(newArray);
      setFilterActive('Completed');
    }

    function showActiveItems() {
      const newArray = data?.filter((data:any) => {
        return data.completed === false;
      })
      handleShowItems(newArray);
      setFilterActive('Active');
    }

    function showAllItems() {
      const newArray = data.map((item:any) => ({...item}) );
      handleShowItems(newArray);
      setFilterActive('All');
    }

    return (
      <div
       className="dark:bg-[#25273c] bg-[#fafafa] transition-colors
         duration-300 flex items-center gap-3 justify-center rounded-[.3rem]
         mt-4 md:mt-0 h-10 md:h-full font-bold dark:text-[#4d5066]
        text-[#9394a5] text-[.8rem] "
      >
        <span
         className={filterActive === 'All' ? selected : normalItem}
         onClick={showAllItems}
        >All</span>

        <span
         className={filterActive === 'Active' ? selected : normalItem}
         onClick={showActiveItems}
        >Active</span>

        <span
         className={filterActive === 'Completed' ? selected : normalItem}
         onClick={showCompletedItems}
        >Completed</span>

      </div>
    )
}

export default memo(FilterList);