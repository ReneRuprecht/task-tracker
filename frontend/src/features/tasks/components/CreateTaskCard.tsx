import { useState } from "react";
import { cn } from "../../../utils/tailwind";

interface CreateTaskCardProps {
  onCreate: (title: string) => void;
  onCancel: () => void;
}

export default function CreateTaskCard({
  onCreate,
  onCancel,
}: CreateTaskCardProps) {
  const [title, setTitle] = useState("");

  const handleSave = () => {
    onCreate?.(title);

    setTitle("");
  };

  return (
    <div
      className={cn(
        "mx-10 rounded-lg border border-dashed border-gray-300",
        "bg-gray-50 px-4 py-3",
        "transition-all duration-300 ease-out",
      )}
    >
      <div className="flex items-center gap-3">
        <div className="h-2.5 w-2.5 rounded-full bg-yellow-500" />

        <input
          autoFocus
          type="text"
          value={title}
          placeholder="New task..."
          onChange={(e) => setTitle(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter") {
              handleSave();
            }

            if (e.key === "Escape") {
              onCancel?.();
            }
          }}
          className={cn(
            "w-full rounded-md border border-gray-200",
            "bg-white px-3 py-2 text-sm",
            "transition-all outline-none",
            "focus:border-blue-400",
          )}
        />
      </div>

      <div className="mt-3 flex justify-end gap-3">
        <button
          onClick={onCancel}
          className={cn(
            "text-sm text-gray-500",
            "transition-colors hover:text-gray-700",
          )}
        >
          Cancel
        </button>

        <button
          disabled={!title.trim()}
          onClick={handleSave}
          className={cn(
            "text-sm font-medium text-green-600",
            "transition-colors hover:text-green-700",
          )}
        >
          Save
        </button>
      </div>
    </div>
  );
}
