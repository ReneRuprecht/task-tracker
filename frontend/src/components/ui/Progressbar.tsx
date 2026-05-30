import { cn } from "../../utils/tailwind";

interface ProgressBarProps {
  value: number;
}

export function ProgressBar({ value }: ProgressBarProps) {
  return (
    <div className="w-full">
      <div className="h-1.5 w-full rounded-full bg-gray-200">
        <div
          role="progressbar"
          className={cn(
            "h-1.5 rounded-full transition-all duration-1000 ease-out",
            value === 100
              ? "bg-green-500"
              : "bg-linear-to-r from-blue-500 to-blue-400",
          )}
          style={{ width: `${value}%` }}
        />
      </div>

      <div className="mt-1 text-xs text-gray-500">{value}% complete</div>
    </div>
  );
}
